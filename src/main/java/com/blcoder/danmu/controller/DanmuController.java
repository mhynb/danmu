package com.blcoder.danmu.controller;

import com.blcoder.danmu.dto.DanmuMessage;
import com.blcoder.danmu.model.Danmu;
import com.blcoder.danmu.service.DanmuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DanmuController {

    private final DanmuService danmuService;

    /**
     * WebSocket消息处理 - 发送弹幕
     */
    @MessageMapping("/danmu/{roomId}")
    @SendTo("/topic/danmu/{roomId}")
    public DanmuMessage sendDanmu(@DestinationVariable String roomId,
                                  DanmuMessage message,
                                  SimpMessageHeaderAccessor headerAccessor) {
        try {
            // 获取客户端IP（WebSocket环境下获取IP比较复杂，这里简化处理）
            String ipAddress = "127.0.0.1";

            message.setRoomId(roomId);
            danmuService.sendDanmu(message, ipAddress);

            return message;
        } catch (Exception e) {
            log.error("WebSocket发送弹幕失败", e);
            // 返回错误消息
            DanmuMessage errorMessage = new DanmuMessage();
            errorMessage.setType("ERROR");
            errorMessage.setContent("发送失败: " + e.getMessage());
            return errorMessage;
        }
    }

    /**
     * HTTP接口 - 发送弹幕
     */
    @PostMapping("/api/danmu/send")
    public Map<String, Object> sendDanmuHttp(@RequestBody DanmuMessage message,
                                           HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            String ipAddress = getClientIpAddress(request);
            danmuService.sendDanmu(message, ipAddress);

            result.put("success", true);
            result.put("message", "弹幕发送成功");
            result.put("data", message);
        } catch (Exception e) {
            log.error("HTTP发送弹幕失败", e);
            result.put("success", false);
            result.put("message", "发送失败: " + e.getMessage());
        }
        return result;
    }

    /**
     * 获取房间最近弹幕
     */
    @GetMapping("/api/danmu/recent/{roomId}")
    public Map<String, Object> getRecentDanmu(@PathVariable String roomId,
                                            @RequestParam(defaultValue = "50") int limit) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Danmu> danmuList = danmuService.getRecentDanmu(roomId, limit);
            result.put("success", true);
            result.put("data", danmuList);
            result.put("count", danmuList.size());
        } catch (Exception e) {
            log.error("获取弹幕失败", e);
            result.put("success", false);
            result.put("message", "获取失败: " + e.getMessage());
        }
        return result;
    }

    /**
     * 获取房间弹幕统计
     */
    @GetMapping("/api/danmu/stats/{roomId}")
    public Map<String, Object> getDanmuStats(@PathVariable String roomId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Long count = danmuService.getDanmuCount(roomId);
            result.put("success", true);
            result.put("totalCount", count);
        } catch (Exception e) {
            log.error("获取弹幕统计失败", e);
            result.put("success", false);
            result.put("message", "获取失败: " + e.getMessage());
        }
        return result;
    }

    /**
     * 获取客户端真实IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0];
        }

        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }

        return request.getRemoteAddr();
    }
}


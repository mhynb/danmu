package com.blcoder.danmu.service.impl;

import com.blcoder.danmu.dto.DanmuMessage;
import com.blcoder.danmu.mapper.DanmuMapper;
import com.blcoder.danmu.model.Danmu;
import com.blcoder.danmu.service.DanmuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DanmuServiceImpl implements DanmuService {

    private final DanmuMapper danmuMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendDanmu(DanmuMessage message, String ipAddress) {
        try {
            // 设置消息ID和时间戳
            message.setMessageId(UUID.randomUUID().toString());
            message.setTimestamp(System.currentTimeMillis());
            message.setType("DANMU");

            // 保存到数据库
            Danmu danmu = new Danmu();
            danmu.setContent(message.getContent());
            danmu.setNickname(message.getNickname());
            danmu.setColor(message.getColor());
            danmu.setType(message.getDanmuType());
            danmu.setSpeed(message.getSpeed());
            danmu.setRoomId(message.getRoomId());
            danmu.setCreateTime(LocalDateTime.now());
            danmu.setIpAddress(ipAddress);

            saveDanmu(danmu);

            // 广播弹幕消息到指定房间
            messagingTemplate.convertAndSend("/topic/danmu/" + message.getRoomId(), message);

            log.info("弹幕发送成功: roomId={}, nickname={}, content={}",
                    message.getRoomId(), message.getNickname(), message.getContent());

        } catch (Exception e) {
            log.error("发送弹幕失败", e);
            throw new RuntimeException("发送弹幕失败: " + e.getMessage());
        }
    }

    @Override
    public List<Danmu> getRecentDanmu(String roomId, int limit) {
        return danmuMapper.selectRecentByRoomId(roomId, limit);
    }

    @Override
    public Long getDanmuCount(String roomId) {
        return danmuMapper.countByRoomId(roomId);
    }

    @Override
    public void saveDanmu(Danmu danmu) {
        danmuMapper.insert(danmu);
    }
}


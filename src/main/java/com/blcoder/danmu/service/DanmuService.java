package com.blcoder.danmu.service;

import com.blcoder.danmu.dto.DanmuMessage;
import com.blcoder.danmu.model.Danmu;

import java.util.List;

public interface DanmuService {

    /**
     * 发送弹幕
     */
    void sendDanmu(DanmuMessage message, String ipAddress);

    /**
     * 获取房间最近弹幕
     */
    List<Danmu> getRecentDanmu(String roomId, int limit);

    /**
     * 获取房间弹幕统计
     */
    Long getDanmuCount(String roomId);

    /**
     * 保存弹幕到数据库
     */
    void saveDanmu(Danmu danmu);
}


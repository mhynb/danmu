package com.blcoder.danmu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanmuMessage {

    /**
     * 消息类型：DANMU-弹幕消息，SYSTEM-系统消息，HEARTBEAT-心跳
     */
    private String type;

    /**
     * 弹幕内容
     */
    private String content;

    /**
     * 发送者昵称
     */
    private String nickname;

    /**
     * 弹幕颜色
     */
    private String color;

    /**
     * 弹幕类型：0-滚动，1-顶部，2-底部
     */
    private Integer danmuType;

    /**
     * 弹幕速度
     */
    private Integer speed;

    /**
     * 房间ID
     */
    private String roomId;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 消息ID
     */
    private String messageId;
}


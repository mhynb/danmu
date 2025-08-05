package com.blcoder.danmu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("danmu")
public class Danmu {

    @TableId(type = IdType.AUTO)
    private Long id;

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
    private Integer type;

    /**
     * 弹幕速度
     */
    private Integer speed;

    /**
     * 房间ID
     */
    private String roomId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * IP地址
     */
    private String ipAddress;
}


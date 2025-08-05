package com.blcoder.danmu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blcoder.danmu.model.Danmu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DanmuMapper extends BaseMapper<Danmu> {

    /**
     * 根据房间ID查询最近的弹幕
     */
    @Select("SELECT * FROM danmu WHERE room_id = #{roomId} ORDER BY create_time DESC LIMIT #{limit}")
    List<Danmu> selectRecentByRoomId(String roomId, int limit);

    /**
     * 根据房间ID统计弹幕数量
     */
    @Select("SELECT COUNT(*) FROM danmu WHERE room_id = #{roomId}")
    Long countByRoomId(String roomId);
}


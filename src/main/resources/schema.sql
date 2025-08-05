-- 弹幕表
CREATE TABLE IF NOT EXISTS danmu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(500) NOT NULL COMMENT '弹幕内容',
    nickname VARCHAR(50) NOT NULL COMMENT '发送者昵称',
    color VARCHAR(20) DEFAULT '#FFFFFF' COMMENT '弹幕颜色',
    type INT DEFAULT 0 COMMENT '弹幕类型：0-滚动，1-顶部，2-底部',
    speed INT DEFAULT 5 COMMENT '弹幕速度',
    room_id VARCHAR(50) NOT NULL COMMENT '房间ID',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    ip_address VARCHAR(50) COMMENT 'IP地址',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志'
);

-- 创建索引
CREATE INDEX idx_danmu_room_id ON danmu(room_id);
CREATE INDEX idx_danmu_create_time ON danmu(create_time);


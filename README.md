# 🎬 实时弹幕系统

基于Spring Boot + WebSocket的实时弹幕系统，支持多房间、实时通信、弹幕样式自定义。

## ✨ 功能特性

- 🚀 **实时通信**: 基于WebSocket的实时弹幕推送
- 🏠 **多房间支持**: 支持多个独立的弹幕房间
- 🎨 **样式自定义**: 支持弹幕颜色、类型、速度自定义
- 📱 **响应式设计**: 适配PC和移动端
- 💾 **数据持久化**: 弹幕数据存储到数据库
- 📊 **统计功能**: 弹幕数量统计和历史查询
- 🔍 **IP记录**: 记录发送者IP地址

## 🛠️ 技术栈

- **后端**: Spring Boot 2.7.18, WebSocket, MyBatis Plus
- **数据库**: H2 (内存数据库)
- **前端**: HTML5, CSS3, JavaScript, SockJS, STOMP
- **构建工具**: Maven

## 🚀 快速开始

### 环境要求

- Java 8+
- Maven 3.6+

### 启动应用

1. 克隆项目
```bash
git clone <repository-url>
cd danmu
```

2. 编译项目
```bash
mvn clean compile
```

3. 启动应用
```bash
mvn spring-boot:run -Dmaven.test.skip=true
```

4. 访问应用
- 首页: http://localhost:8080
- H2数据库控制台: http://localhost:8080/h2-console

## 📖 使用说明

### 1. 首页 (/)
- 输入房间ID进入弹幕房间
- 提供快速进入预设房间的按钮
- 显示系统功能特性

### 2. 弹幕房间 (/room/{roomId})
- 实时显示弹幕效果
- 支持三种弹幕类型：滚动、顶部、底部
- 可以直接在房间内发送弹幕
- 查看历史弹幕记录
- 显示连接状态和统计信息

### 3. 弹幕发送页面 (/send/{roomId})
- 专门的弹幕发送界面
- 实时预览弹幕效果
- 支持快速消息选择
- 发送统计和状态显示

## 🎯 弹幕类型

- **滚动弹幕 (type=0)**: 从右到左滚动显示
- **顶部弹幕 (type=1)**: 在屏幕顶部固定显示
- **底部弹幕 (type=2)**: 在屏幕底部固定显示

## 🔧 API接口

### HTTP接口

- `POST /api/danmu/send` - 发送弹幕
- `GET /api/danmu/recent/{roomId}` - 获取最近弹幕
- `GET /api/danmu/stats/{roomId}` - 获取房间统计

### WebSocket接口

- 连接端点: `/ws-danmu`
- 发送消息: `/app/danmu/{roomId}`
- 订阅频道: `/topic/danmu/{roomId}`

## 📊 数据库

使用H2内存数据库，表结构：

```sql
CREATE TABLE danmu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(500) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    color VARCHAR(20) DEFAULT '#FFFFFF',
    type INT DEFAULT 0,
    speed INT DEFAULT 5,
    room_id VARCHAR(50) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(50),
    deleted INT DEFAULT 0
);
```

## 🎨 自定义配置

可以通过修改 `application.properties` 来自定义配置：

```properties
# 服务器端口
server.port=8080

# 数据库配置
spring.datasource.url=jdbc:h2:mem:danmu
spring.datasource.username=sa
spring.datasource.password=

# 日志级别
logging.level.com.blcoder.danmu=DEBUG
```

## 🔍 开发调试

1. **查看数据库**: 访问 http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:danmu`
   - 用户名: `sa`
   - 密码: (空)

2. **查看日志**: 应用启动后会在控制台显示详细日志

3. **WebSocket调试**: 可以使用浏览器开发者工具查看WebSocket连接状态

## 📝 项目结构

```
src/main/java/com/blcoder/danmu/
├── config/          # 配置类
│   └── WebSocketConfig.java
├── controller/      # 控制器
│   ├── DanmuController.java
│   └── PageController.java
├── dto/            # 数据传输对象
│   └── DanmuMessage.java
├── mapper/         # 数据访问层
│   └── DanmuMapper.java
├── model/          # 实体类
│   └── Danmu.java
├── service/        # 服务层
│   ├── DanmuService.java
│   └── impl/
│       └── DanmuServiceImpl.java
└── DanmuApplication.java

src/main/resources/
├── templates/      # 前端页面
│   ├── index.html
│   ├── room.html
│   └── send.html
├── application.properties
└── schema.sql
```

## 🤝 贡献

欢迎提交Issue和Pull Request来改进这个项目！

## 📄 许可证

MIT License


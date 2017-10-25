# AIChatRobot
设计的一个聊天机器人原型，Android客户端+PC服务器

# 关于通信
基于UDP协议，对于聊天机器人来说，客户端点击按钮的频率高，对丢包的容错率高
UDP下的socket方便实现
对于服务器而言，读取数据报--解析--查询数据库--返回数据报是一种更方便合理的方式

# Android
采用JAVA实现通信部分

# PC服务器
采用C++搭建本地服务器，Addr：127.0.0.1 Port：8989

# 数据库
采用MySQL5.7

# 文件夹说明
database：用于存放mysql中导出的数据库文件，在编程时不要更改数据库的结构；
doc：用于存放设计文档；
src_Android：用于存放Android客户端工程；
src_PC：用于存放PC服务器源码；

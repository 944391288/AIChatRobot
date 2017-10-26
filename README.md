# AIChatRobot
设计的一个聊天机器人原型，Android客户端+PC服务器

# 关于通信
基于TCP协议，对于聊天机器人来说，模拟连接到人的情况，保证通信质量是更加重要的。
对于服务器而言，通过TCP的三次握手，保证收到的信号的安全性。

# Android
采用JAVA实现通信部分

# PC服务器
采用JAVA搭建本地服务器，Addr：127.0.0.1 Port：8989

# 数据库
采用MySQL5.7

# 文件夹说明
database：用于存放mysql中导出的数据库文件，在编程时不要更改数据库的结构；
doc：用于存放设计文档；
src_Android：用于存放Android客户端工程；
src_PC：用于存放PC服务器源码；

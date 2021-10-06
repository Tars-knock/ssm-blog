# 直接从docker提供的远程镜像仓库中拉取最新的tomcat组件
FROM tomcat:9.0.44

# 本镜像的制作者的信息
MAINTAINER "Tars-knock" <sfly13344@163.com>

# 把该容器的时区设置为北京时区
ENV TZ=Asia/Shanghai

EXPOSE 8080

# 把当前文件目录下的docker-ssm.war移动到容器中tomcat自动启动的目录下
COPY ./ssm_blog.war /usr/local/tomcat/webapps/

# 容器启动时执行指令
# 容器启动时启动tomcat并打印日志
ENTRYPOINT /usr/tomcat/bin/startup.sh && tail -f /usr/tomcat/logs/catalina.out

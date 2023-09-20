# 使用官方的OpenJDK 11作为基础镜像
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

# 复制整个Spring Boot项目到镜像中
COPY Docker .

# 构建Spring Boot项目，你可能需要根据你的项目构建工具进行调整
RUN ./mvnw clean package -DskipTests

# 暴露应用程序的端口（如果需要）
EXPOSE 8080

# 启动Spring Boot应用程序
CMD ["java", "-jar", "target/ssm-blog.jar"]

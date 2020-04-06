#项目所依赖的镜像
FROM java:8
#设置静态目录
RUN mkdir /static
# 设置时区
ENV TZ=Asia/Shanghai
# 时区写入系统文件
RUN ln -snf /usr/share/zoneinfo/$TZ  /etc/localtime && echo $TZ > /etc/timezone
# 将maven构建好的jar添加到镜像中
ADD ./target/*.jar app.jar
# 暴露的端口号
EXPOSE 8088
# 镜像所执行的命令
ENTRYPOINT ["java","-jar","/app.jar"]
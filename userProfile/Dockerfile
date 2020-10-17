FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8010
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY data.json /app/data.json
ADD target/sms-group.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
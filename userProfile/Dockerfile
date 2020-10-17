FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8010
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/sms-group.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
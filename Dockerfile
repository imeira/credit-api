FROM mongo
EXPOSE 27017

FROM openjdk:11
LABEL SERVICE_NAME=credit-api

ENV LANG en_US.UTF-8
ENV APP_NAME credit-api.jar
ENV APP_HOME .

WORKDIR /app
COPY credit-api/target/credit-api.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

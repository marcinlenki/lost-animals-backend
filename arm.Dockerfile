FROM arm64v8/amazoncorretto:17.0.5
ENV APP_PORT=8080
EXPOSE ${APP_PORT}

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
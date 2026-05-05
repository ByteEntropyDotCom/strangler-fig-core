# Stage 1: Extract layers
FROM eclipse-temurin:21-jre-alpine as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Stage 2: Final Runtime Image
FROM eclipse-temurin:21-jre-alpine
WORKDIR application

# Best practice: Don't run as root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

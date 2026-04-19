FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests -q
RUN mv target/*.jar deployment-app.jar

FROM eclipse-temurin:22-jre-alpine

WORKDIR /app
COPY --from=build /app/deployment-app.jar .
COPY global-bundle.pem .
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-XshowSettings:vm", "-jar", "deployment-app.jar"]
EXPOSE 8085
FROM eclipse-temurin:21-jre

COPY target/flowOps-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/app.jar"]
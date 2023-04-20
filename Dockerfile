FROM openjdk:17.0.1-jdk-slim
MAINTAINER BERDAN_AKBULUT
COPY target/product-service.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]
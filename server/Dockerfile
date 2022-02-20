FROM maven:3.8.4-openjdk-17-slim AS build
RUN mkdir /opt/loqui
WORKDIR /opt/loqui
COPY . /opt/loqui
RUN mvn clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /opt/loqui/target/loqui.jar loqui.jar
EXPOSE 8080
ENTRYPOINT java -jar loqui.jar
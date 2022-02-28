FROM adoptopenjdk/openjdk8
COPY target/hello.jar hello.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "hello.jar"]
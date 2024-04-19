FROM openjdk:17
VOLUME /tmp
EXPOSE 8020
ADD ./target/books-0.0.1-SNAPSHOT.jar books-app.jar
ENTRYPOINT ["java", "-jar", "books-app.jar"]
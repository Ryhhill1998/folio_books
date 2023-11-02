FROM maven as build

COPY pom.xml pom.xml
RUN mvn dependency:resolve

COPY src src
RUN mvn package

FROM openjdk:17-jdk-alpine

COPY --from=build target/folio_books-0.0.1-SNAPSHOT.jar folio_books-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "folio_books-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080:8080
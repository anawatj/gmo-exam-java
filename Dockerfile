FROM openjdk:17
COPY target/exam-0.0.1-SNAPSHOT.jar exam-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "exam-0.0.1-SNAPSHOT.jar"]
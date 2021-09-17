FROM openjdk:15
VOLUME /tmp
ADD ./target/springboot-estadistica-0.0.1-SNAPSHOT.jar estadistica.jar
ENTRYPOINT ["java","-jar","/estadistica.jar"]
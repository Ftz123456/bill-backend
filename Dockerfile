FROM openjdk:18
LABEL authors="fantianzhi"
ADD target/bill-0.0.1-SNAPSHOT.jar /usr/src/bill/
WORKDIR /usr/src/bill
EXPOSE 8081
CMD ["java", "-jar", "bill-0.0.1-SNAPSHOT.jar"]



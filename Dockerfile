FROM openjdk:17-jdk AS spring
WORKDIR /task-processor
EXPOSE 8080
CMD java -jar "/task-processor/task-processor.jar"

ADD target/task-processor.jar /task-processor
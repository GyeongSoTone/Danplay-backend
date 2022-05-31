FROM amazoncorretto:11.0.14

EXPOSE 2021

ARG JAR_FILE=build/libs/*.jar

ADD ${JAR_FILE} danplay_be_image.jar

ENTRYPOINT ["java","-jar","/danplay_be_image.jar"]

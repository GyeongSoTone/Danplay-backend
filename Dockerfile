
# jdk 설정
FROM amazoncorretto:11.0.14-alpine

# 포트 노출
EXPOSE 2021

# 매개변수 선언 - 빌드된 jar파일
ARG JAR_FILE=build/libs/*.jar

#
ADD ${JAR_FILE} spring_docker_01.jar


ENTRYPOINT ["java","-jar","/spring_docker_01.jar"]

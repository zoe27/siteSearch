FROM maven:3-jdk-8 AS MAVEN_BUILD
MAINTAINER zoe

COPY * /build/
#COPY site-es-v1/* /build/
#COPY site-page/* /build/
WORKDIR /build/
RUN pwd
RUN ls /build/
RUN mvn clean -Dmaven.test.skip=true install -f pom.xml
FROM java:8
COPY --from=MAVEN_BUILD /build/target/*.jar ./site.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","site.jar"]

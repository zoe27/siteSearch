FROM maven:3-jdk-8 AS MAVEN_BUILD
MAINTAINER zoe

WORKDIR /build/
COPY pom.xml /build/
COPY site-es-v1 /build/site-es-v1
COPY site-page /build/site-page
RUN pwd
RUN mvn clean -Dmaven.test.skip=true install -f pom.xml
RUN ls /build/
FROM java:8
COPY --from=MAVEN_BUILD /build/site-page/target/*.jar ./site.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","site.jar"]

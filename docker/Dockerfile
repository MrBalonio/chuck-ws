FROM openjdk:jre-alpine

ADD chuck-ws-swarm.jar /opt/wildfly-swarm.jar

-Dswarm.context.path=/chuck-ws
EXPOSE 8080
ENTRYPOINT ["java","-Dswarm.context.path=/chuck-ws","-jar", "/opt/wildfly-swarm.jar"]

# Balonio chuck joke api
## Run with
```
mvn -Dswarm.context.path=/chuck-ws -Dswarm.http.port=7080 clean package
wildfly-swarm:run
```
Then:
```
curl -vvv -k  http://localhost:7080/chuck-ws/fact/random
```

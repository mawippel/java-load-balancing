# java-load-balancing

To run the server:

```
java "-Dserver.port=8081" -jar .\say-hello-0.0.1-SNAPSHOT.jar
```
or
```
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8080"
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8082"
```

To build the dockerfile:

```
docker build -t server-side .
docker tag server-side mawippel/load-balance-server-side
docker push mawippel/load-balance-server-side
```

To run the images

```
docker pull mawippel/load-balance-server-side
docker run -p 8080:8090 server-side
docker run -p 8081:8090 server-side
docker run -p 8082:8090 server-side
```

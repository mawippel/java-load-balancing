# java-load-balancing

To run the server:

```
java "-Dserver.port=8081" -jar .\say-hello-0.0.1-SNAPSHOT.jar
```

To build the dockerfile:

```
docker build -t server-side . --build-arg PORT=8081
```

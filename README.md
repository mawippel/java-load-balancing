# java-load-balancing
Client-Server application to see the NASA's APOD.
Runs in a 3 instance Cluster, Load Balanced by Ribbon.

### Running the server:

```
cd say-hello
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8080"
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8082"
```
With this, 3 instances of the server will be running locally.

#### Running the server with Docker
```
docker pull mawippel/load-balance-server-side
docker run -p 8080:8090 mawippel/load-balance-server-side
docker run -p 8081:8090 mawippel/load-balance-server-side
docker run -p 8082:8090 mawippel/load-balance-server-side
```

### Running the client:

```
cd user
mvn spring-boot:run"
```
With this, your client will be avaible to receive requests.

## Running the application:
When accessing ```http://localhost:8888/getAPOD```, you'll be automatically redirected to the APOD's picture page.

## Building a new version
To build the dockerfile:

```
docker build -t server-side .
docker tag server-side:version_here mawippel/load-balance-server-side
docker login
docker push mawippel/load-balance-server-side
```

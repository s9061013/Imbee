# Imbee
Imbee odd-site test

### check before install
#### application.yml
scroll to the bottom (env variable) check Mysql, RabbitMQ, Firebase variable of your own
#### src/main/resources/firebase-service-account.json
download from firebase about the account info

### cmd
#### package the project
```
mvn clean package
```
or you can use ide 

#### build an image
```
docker build -t {IMAGE_NAME} .
```

#### docker run
```
docker run -p 8081:8081 {DOCKER_NAME}
```

### other info 
I add flyway to control the version of the DB schema,
and swagger ui to test the api. and record with log
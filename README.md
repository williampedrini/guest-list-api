# GUEST LIST API

This project contains the representation of an API responsible for performing the management of a guest list for a party. 

## ASSUMPTIONS

```
If it is expected that the guest's table can accommodate the extra people, then the whole party should be let in.
```
- We should have a way to register all the guest in a table and associate it to a table.
- We should have a way to register all the tables of a party and define the amount of seats of that table.

```
Guests will also leave throughout the course of the party. Note that when a guest leaves, their accompanying guests will leave with them.
```
- No one can use the guest's table even if this guest has left.
- Once they leave, they cannot go back.

## RUNNING IN DOCKER 

These instructions will get you the project up and running in a Docker container for testing purposes.

It will download the application image from [docker registry](https://hub.docker.com/repository/docker/williamcustodio/guest-list-api) and configure the database.

### PREREQUISITES

What things you need to run the application:

```
Docker
```

#### INSTALLING

##### DOCKER

Download the [wizard](https://docs.docker.com/get-docker/) and follow the instructions to install it.

### RUNNING

Run the application with the following command inside the [root folder](.):

```
 docker-compose up
```

## RUNNING IN DEVELOPMENT MODE

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### PREREQUISITES

What things you need to install the application and how to install them:

```
Java 11
Maven 3.8.5
Docker
```
#### INSTALLING

Install the tool for managing parallel versions of multiple Software Development Kits [SDK MAN](https://sdkman.io/install).

##### JAVA

Execute the following command:

```
sdk install java 11.0.14.9.1-amzn
```

##### MAVEN

Execute the following command:

```
sdk install maven 3.8.5
```

##### DOCKER

Download the [wizard](https://docs.docker.com/get-docker/) and follow the instructions to install it.

### RUNNING

Create an instance of MySQL using the following command:

```
 docker run -e MYSQL_ROOT_PASSWORD=<root_password> -e MYSQL_DATABASE=<database_name> -e MYSQL_USER=<username> -e MYSQL_PASSWORD=<password> -p 3306:3306 mysql
```

Run the application with the following command inside the [root folder](.):

```
 mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DDATA_SOURCE_URL=jdbc:mysql://localhost:3306/<database_name> -DDATA_SOURCE_USERNAME=<username> -DDATA_SOURCE_PASSWORD=<password> -DLIQUIBASE_ENABLED=true"
```

## RUNNING TESTS

### SwaggerUI

1. Access the [api documentation](http://localhost:8080/swagger-ui.html).
2. Perform tests using the defined requests.

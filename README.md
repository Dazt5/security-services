# Security service project

Project is organized using modules and following **Hexagonal Architecture**:

## Modules:
- code-domain: inside this module are the domain entities, use cases and ports that will interact with the infrastructure.
- adapters-incoming: inside this module we will found the classes that will interact with third-party APIs or handle outside calls.
- adapters-outgoing: there are the data persistence classes and integration with third-party systems.
- app: general project configuration and aspects related to the app infrastructure.

Ports define contracts in the core, adapters-incoming or adapters-outgoing implements these contracts and the core never knows about JPA, BCrypt or Rest, only interfaces.

So in adapters-outgoing we implement our controller which will inject a port(optionally it can inject a service that works like a wrapper for ports)
then these ports will interact with other ports which contracts will be defined in the core but its implementations can be on the adapters packages.

## Database configuration(required)

### Database required env variables:

> [!IMPORTANT]
> To run the app, you need to set the following env variables related to database configuration:

- `APP_CONTEXTS_ENABLES`: liquibase context, it will decide if app have to run some liquibase scripts e.g `local-dev` / `dev` / `prod`
- `SECURITY_SERVICES_DBMS`: will define the dbms to connect e.g: `postgresql` / `mysql`
- `SECURITY_SERVICES_DB_HOST`: database host e.g: `127.0.0.1`
- `SECURITY_SERVICES_DB_PORT`: database port e.g: `5432` / `3306`
- `SECURITY_SERVICES_DB_NAME`: database name e.g `security_services`
- `SECURITY_SERVICES_DB_USER`: database user e.g `security_services_user`
- `SECURITY_SERVICES_DB_PASSWORD`: user password e.g `autogeneratedPassword`
- `SECURITY_SERVICES_DB_DRIVER`: driver that JPA will use to connect to the database e.g `org.postgresql.Driver` / `com.mysql.cj.jdbc.Driver`
- `SECURITY_SERVICES_HIBERNATE_DIALECT`: dialect used for hibernate to connect to the database e.g `org.hibernate.dialect.PostgreSQLDialect` / `org.hibernate.dialect.MySQL8Dialect`

### Setup postgres database thru .ssh scripts(Optional):

> [!TIP]
> In order to be able to run the project, we've created some scripts that will help you to create a postgres database,
> only software needed is docker, this is completely optional.

Inside module `adapters-outgoing/src/main/resource`, there is a docker-compose script with an autoconfigured postgres database.

to complete the setup to use the docker-compose script first you must configure the following env variables:

- `POSTGRES_DEFAULT_USER_NAME`: postgres root user e.g: `postgres`
- `POSTGRES_DEFAULT_USER_PASSWORD`: postgres root user password e.g `password`

inside folder `adapters-outgoing/src/main/resource/scripts/`, there is a sh script that automatically 
create a database for the project.

After configure these env variables, you should be able to run typing `docker-compose up`, make sure ports set in docker-compose
are available.

## Setup Authentication(required)

To sign and validate Jwt tokens you need to create an encoded-base64 secret and set it up in an env variable called ``JWT_SECRET``

This secret will be used to sign and check Jwt tokens.

> [!TIP]
> You can create this secret using test method `testGenerateSecret` in class `JwtServicesTest` localed in `common` module.
> full path: ```/security-services/common/src/test/kotlin/com/alejandra/security/jwt/JwtServicesTest.kt```

- TODO: It will be leverage, the idea is to encrypt the secret and decrypt it each time we need to verify a token.

## Docker 

### Build
Use: `docker build -t security-services:latest .`

### Deploying in a container
Use: 
```
docker run --name security-services-container \
  -e SECURITY_SERVICES_DB_NAME \
  -e SECURITY_SERVICES_DB_USER \
  -e SECURITY_SERVICES_DB_PASSWORD \
  -e SECURITY_SERVICES_DBMS \
  -e SECURITY_SERVICES_DB_HOST \
  -e SECURITY_SERVICES_DB_PORT \
  -e SECURITY_SERVICES_DB_DRIVER \
  -e APP_CONTEXTS_ENABLES \
  -e JWT_SECRET \
  -p 8080:8080 \
  security-services:latest
```
Would recommend you to copy .env_example file in root with the name .env and use:
```
docker run --name security-services-container --env-file .env -p 8080:8080 security-services:latest
```
If your database is in a different container you should create a network and add both containers into that network.
```
docker run --name security-services-container --env-file .env --network postgres_pg_security_services_network -p 8080:8080 security-services:latest
```

## Some TODOs needed to be completed

should I create a trello board to trace all this stuff?, yes, but anyway.

- Fix TODOs comments leaved in code
- Integrate PMD Lib and use github action(or something) to check test, sonar(if possible) and PMDs
- create some interesting git pre-commit hooks
- remove this section when all Trello board is created.
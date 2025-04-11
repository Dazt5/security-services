### Build phase
FROM maven:3.9-eclipse-temurin-17 AS builder

# workdir in container, so the files we want to copy or move will be there.
WORKDIR /build

# copying poms
COPY pom.xml .
COPY adapters-incoming ./adapters-incoming
COPY adapters-outgoing ./adapters-outgoing
COPY app ./app
COPY common ./common
COPY core-domain ./core-domain

# download dependencies without compile and save them in cache.
RUN mvn dependency:go-offline -B

# Copying whole source code
COPY . .

# Compile JAR
# -pl: project list, only build module refered in -pl, in this case is app
# -am: also make, also build modules that depends on app(project dependencies not from mvn central)
RUN mvn clean package -pl app -am -DskipTests

### Deploying phase
FROM eclipse-temurin:17-jdk-alpine


WORKDIR /app

# Copying jar from builder workdir
COPY --from=builder /build/app/target/*.jar app.jar

# Exposing
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
# Uso una imagen base de Java
FROM openjdk:8-jdk-alpine


RUN apk add --no-cache maven

# Directorio 
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/ejercicio-1.0-SNAPSHOT.jar"]
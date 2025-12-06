# Etapa 1: Build usando Maven + JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código
COPY src ./src

# Gera o jar
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final apenas com JDK + JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

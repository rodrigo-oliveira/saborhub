
# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml first to leverage Docker cache
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application e garanta nome fixo do JAR
RUN mvn clean package -DskipTests && cp target/*.jar target/saborhub.jar

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copie o JAR final
COPY --from=build /app/target/saborhub.jar app.jar

# (Opcional) Copie arquivos de configuração externos, se necessário
# COPY --from=build /app/src/main/resources/application-docker.properties ./application-docker.properties

# Exponha a porta padrão do Spring Boot
EXPOSE 8080

# Defina variáveis de ambiente padrão, se necessário
# ENV SPRING_PROFILES_ACTIVE=docker

# Execute a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

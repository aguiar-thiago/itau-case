# Usar uma imagem base com OpenJDK 17
FROM openjdk:17-jdk-slim

RUN mkdir /app

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR da aplicação para o container
COPY target/*.jar /app/app.jar

# Expor a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Definir o comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
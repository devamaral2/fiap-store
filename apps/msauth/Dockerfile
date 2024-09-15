# Etapa 1: Construir a aplicação
FROM jelastic/maven:3.9.5-openjdk-21 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos de configuração do Maven
COPY pom.xml ./
COPY src ./src

# Construir a aplicação
RUN mvn clean package -Dmaven.test.skip=true

# Etapa 2: Criar a imagem final
FROM openjdk:21-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR da etapa de construção
COPY --from=build /app/target/*.jar app.jar

# Expor a porta em que a aplicação irá rodar
EXPOSE 8081

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
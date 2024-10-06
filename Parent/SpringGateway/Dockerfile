# Utiliser l'image Eclipse Temurin comme base (Java 17, slim)
FROM eclipse-temurin:17-jdk-alpine

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré dans le conteneur
COPY target/SpringGateway-0.0.1-SNAPSHOT.jar /app/SpringGateway.jar

# Exposer le port sur lequel l'application va tourner
EXPOSE 8080

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "SpringGateway.jar"]
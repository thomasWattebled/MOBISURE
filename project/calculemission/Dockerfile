# Utiliser l'image officielle de Tomcat
FROM tomcat:10.1-jdk17

# Copier l'application dans le répertoire de Tomcat
COPY target/calculemission.war /usr/local/tomcat/webapps/

# Exposer le port 8080
EXPOSE 8086

# Lancer Tomcat
CMD ["catalina.sh", "run"]
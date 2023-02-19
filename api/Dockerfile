FROM maven:3.8.4-eclipse-temurin-11

WORKDIR /usr/src/mymaven

COPY bin .

RUN mvn package

RUN chmod +x /usr/src/mymaven/target/*.jar

CMD ["java", "-jar", "/usr/src/mymaven/target/DropWizardHewlloWorldExample-FINAL.jar", "server", "hello-world.yml"]

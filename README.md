# java-web-maven-spring-ssl-dropwizard-api-basic-auth-hello-world

## Description
A POC for spring resttemplate calling dropwizard api with basic authentication.

## Tech stack
- java
- maven
  - dropwizard
- gradle
  - spring

## Docker stack
- maven:3-openjdk-17

## To run
`sudo ./install.sh -u`
- Endpoints
  - curl -i localhost/
  - curl -i localhost/api

## To stop (optional)
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`

## Credit
- [Api code based on](https://howtodoinjava.com/dropwizard/dropwizard-basic-auth-security-example/)
- [RestTemplate code](https://www.javacodemonk.com/spring-resttemplate-basic-authentication-f66b7e45)

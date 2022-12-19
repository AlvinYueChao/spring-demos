# Config Server
## Remote configuration
### application.yml
```yaml
spring:
  application:
    name: spring-cloud-config-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/AlvinYueChao/Spring-Cloud.git
          search-paths: Config-Repository
          username: ${git.username}
          password: ${git.password}
          timeout: 600
          skip-ssl-validation: true
      # actual git-pull branch, spring.cloud.config.server.git.default-label is not working
      label: main

server:
  port: 8001

encrypt:
  key-store:
    alias: alias test
    location: test.jks
    secret: ${keypass}
    password: ${storepass}
```
### request
```http request
###
GET http://localhost:8001/config/dev

###
GET http://localhost:8001/config/prod
```
### result
```json lines
{
  "name": "config",
  "profiles": [
    "dev"
  ],
  "label": null,
  "version": "e3e2ace3d52fbf632e306e4bb3ec4bc95a2a9393",
  "state": null,
  "propertySources": [
    {
      "name": "https://github.com/AlvinYueChao/Spring-Cloud.git/Config-Repository/application-dev.yml",
      "source": {
        "app.version": "dev",
        "message": "Spring Cloud Config Demo",
        "spring.rabbitmq.host": "localhost",
        "spring.rabbitmq.port": 5672,
        "spring.rabbitmq.username": "guest",
        "spring.rabbitmq.password": "guest",
        "server.port": 8008
      }
    }
  ]
}

{
  "name": "config",
  "profiles": [
    "prod"
  ],
  "label": null,
  "version": "e3e2ace3d52fbf632e306e4bb3ec4bc95a2a9393",
  "state": null,
  "propertySources": [
    {
      "name": "https://github.com/AlvinYueChao/Spring-Cloud.git/Config-Repository/application-prod.yml",
      "source": {
        "app.version": "prod",
        "message": "Spring Cloud Config Demo",
        "spring.rabbitmq.host": "localhost",
        "spring.rabbitmq.port": 5672,
        "spring.rabbitmq.username": "guest",
        "spring.rabbitmq.password": "guest",
        "server.port": 8008
      }
    }
  ]
}
```
## Local Configuration
### application.yml
```yaml
spring:
  profiles:
    active: native
  application:
    name: spring-cloud-config-server
  cloud:
    config:
      server:
        native:
          search-locations: file:D:/WorkSpace/Java/Gradle/spring-demos/spring-cloud-config-server/properties/

server:
  port: 8001

encrypt:
  key-store:
    alias: alias test
    location: test.jks
    secret: ${keypass}
    password: ${storepass}
```
### request
```http request
###
GET http://localhost:8001/config/qa
```
### result
```json
{
  "name": "config",
  "profiles": [
    "qa"
  ],
  "label": null,
  "version": null,
  "state": null,
  "propertySources": [
    {
      "name": "file:/D:/WorkSpace/Java/Gradle/spring-demos/spring-cloud-config-server/properties/application-qa.yml",
      "source": {
        "app.version": "qa",
        "message": "Spring Cloud Config Demo",
        "spring.rabbitmq.host": "localhost",
        "spring.rabbitmq.port": 5672,
        "spring.rabbitmq.username": "guest",
        "spring.rabbitmq.password": "guest",
        "server.port": 8008
      }
    }
  ]
}
```
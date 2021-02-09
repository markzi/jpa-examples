# JPA Examples

To show the different JPA mappings and relationships and how these can be implemented

Depends on databases running from the (flyway-database)[https://github.com/markzi/flyway-databases] project
- posgres

## TODO

### Cascade Types

### Fetch Types

### One To One

### One To Many / Many To One

#### One Way

#### Bidirectional

## Thoughts

- include flyway and all sql for standalone project as a microservice should be
  -  [database-per-service](https://microservices.io/patterns/data/database-per-service.html)

## Running

In the ```flyway-database``` project run
```shell
./docker-compose-postgres-up.sh
```
- Server port is ```8081``` 
- Swagger endpoint ```http://localhost:8081/swagger-ui/```

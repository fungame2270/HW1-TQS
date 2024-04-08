# HW1-TQS
A simple full-stack web application supplied with automated tests.



# Executar projeto
### Backend (BusAppAPI)
- executar docker compose para a base de dados
```sh
docker-compose up # executar
docker-compose down -v # remover volumes
```
- executar spring-boot
```sh
mvn spring-boot:run # executar app
mvn test # executar tests
```
### Frontend (busfrontend)
- executar o react
```sh
npm install # installar dependencia
npm start # executar react
```
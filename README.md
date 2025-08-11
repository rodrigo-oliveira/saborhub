
# SaborHub

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

SaborHub é uma aplicação Java Spring Boot para gerenciamento de usuários, utilizando PostgreSQL como banco de dados. O projeto está preparado para desenvolvimento com hot reload usando Spring Boot DevTools e Docker Compose Watch.

## Como rodar em modo desenvolvimento (hot reload)
1. Clone o repositório:
	```sh
	git clone https://github.com/rodrigo-oliveira/saborhub
	cd saborhub
	```
2. Suba os containers com watch:
	```sh
	docker compose up --watch
	```
3. Edite o código normalmente. As alterações serão sincronizadas automaticamente e a aplicação será reiniciada pelo DevTools.

## Endpoints principais
- `POST /usuario` — Cadastra um novo usuário
- `GET /usuario` — Lista todos os usuários
- `GET /usuario/{id}` — Busca usuário por ID
- `POST /login` — Realiza login do usuário
- `GET /logout` — Realiza logout do usuário

## Banco de dados
- O serviço `db` usa a imagem oficial do Postgres e persiste dados em volume Docker.
- Configurações de acesso estão em `docker-compose.yml` e `application-docker.properties`.

## Observações
- Para produção, utilize o build do JAR e ajuste o Dockerfile para rodar `java -jar app.jar`.
- O hot reload só funciona em modo desenvolvimento (`mvn spring-boot:run`).
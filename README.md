
# SaborHub

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

SaborHub é uma aplicação Java Spring Boot para gerenciamento de usuários, utilizando PostgreSQL como banco de dados. O projeto está configurado para desenvolvimento com hot reload via Spring Boot DevTools e Docker Compose.

`💡 Recomendação: A forma recomendada de executar o projeto é utilizando Docker Compose, pois simplifica a configuração e garante um ambiente de desenvolvimento consistente.`

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.4
- PostgreSQL 17
- Docker & Docker Compose
- Spring Boot DevTools (hot reload)
- Flyway (migração e versionamento do banco de dados)

## 🐳 Executando em Modo Desenvolvimento com Docker Compose (Recomendado)

Para rodar a aplicação utilizando Docker, siga os passos abaixo:

1. Clone o repositório:
	```sh
	git clone https://github.com/rodrigo-oliveira/saborhub
	cd saborhub
	```

2. Suba os containers
	```sh
	docker compose up
	```

3. Acesse a aplicação
- Por padrão, a aplicação ficará disponível em:
`http://localhost:8080`

## 💻 Executando em Modo Desenvolvimento Local (sem Docker)

1. Clone o repositório:
	```sh
	git clone https://github.com/rodrigo-oliveira/saborhub
	cd saborhub
	```

2. Instale e configure o PostgreSQL 17 na porta 5432
- Certifique-se de criar o banco de dados e usuário conforme o arquivo application.properties.

3. Execute a aplicação
- Abra o projeto na sua IDE de preferência.
- Rode o método main da classe SaborhubApplication.

4. Acesse a aplicação
- Por padrão, a aplicação ficará disponível em:
`http://localhost:8080`

## Endpoints

- `POST /autenticacao/entrar` - Autenticação de usuário
- `GET  /usuario` - Listar todos os usuário
- `POST /usuario` - Cadastrar novo usuário
- `GET  /usuario/:id` - Buscar usuário por id
- `PUT  /usuario` - Atualizar dados do usuário
- `PUT  /usuario/alterar-senha` - Alterar Senha do Usuário
- `DELETE  /usuario/:id` - Remover usuário

## Banco de dados

- O serviço `db` usa a imagem oficial do Postgres e persiste dados em volume Docker.
- Configurações de acesso estão em `docker-compose.yml` e `application-docker.properties`.
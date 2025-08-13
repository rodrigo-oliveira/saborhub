
## 🍔 SaborHub

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

O sistema terá três tipos de usuários: administrador, dono de restaurante e cliente, com campos como nome, email, login, senha, data da última alteração e endereço. O projeto está configurado para rodar em um ambiente Docker com Docker Compose, integrado a um banco de dados relacional PostgreSQL.

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

## 🔌 Endpoints

Autenticação:
- `POST /autenticacao/entrar` - Para um usuário fazer login e obter um token de autenticação.


Gerenciamento de Usuários:
- `GET /usuario` - Lista todos os usuários cadastrados.
- `POST /usuario` - Cria um novo usuário.
- `GET /usuario/{id}` - Busca um usuário específico pelo seu ID.
- `PUT /usuario` - Atualiza os dados de um usuário.
- `PUT /usuario/alterar-senha` - Altera a senha de um usuário.
- `DELETE /usuario/{id}` - Remove um usuário pelo seu ID.


## 📝 Collections para Teste

Link para a Collection do Postman:
https://github.com/rodrigo-oliveira/saborhub/blob/main/SaborHub%20-%20Fluxos%20de%20Uso.postman_collection.json

Importe a collection no Postman, clique com o botão direito sobre ela e selecione a opção “Run” para rodar os fluxos de uso da aplicação.

## 🗄️ Banco de dados

- O serviço `db` usa a imagem oficial do Postgres e persiste dados em volume Docker.
- Configurações de acesso estão em `docker-compose.yml` e `application-docker.properties`.
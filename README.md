
## 🍔 SaborHub

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

Na região, diversos restaurantes enfrentam dificuldades para arcar com os custos de sistemas de gestão individuais. Para contornar esse desafio, eles decidiram unir recursos e contratar estudantes para desenvolver um sistema de gestão compartilhado.
Esse sistema deverá facilitar a administração dos estabelecimentos e oferecer funcionalidades para que os clientes possam escolher restaurantes pela qualidade da comida, consultar informações, realizar avaliações e efetuar pedidos online.
Por questões financeiras, o desenvolvimento será realizado em fases, permitindo implantação gradual, com ajustes e melhorias contínuas baseadas no uso real e no feedback dos usuários.

### Objetivo do Projeto
O projeto tem como objetivo criar um sistema de gestão compartilhado para atender às necessidades dos restaurantes e de seus clientes.
O sistema contará com três tipos de usuários — administrador, dono de restaurante e cliente — cada um com informações como nome, e-mail, login, senha, data da última alteração e endereço.
A solução foi desenvolvida para execução em ambiente Docker, utilizando Docker Compose e um banco de dados relacional PostgreSQL, garantindo escalabilidade e facilidade de implantação.

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


## 📝 Collection para Teste

Link para a Collection do Postman:
https://github.com/rodrigo-oliveira/saborhub/blob/main/SaborHub%20-%20Fluxos%20de%20Uso.postman_collection.json

Importe a collection no Postman, clique com o botão direito sobre ela e selecione a opção “Run” para rodar os fluxos de uso da aplicação.

## 🗄️ Banco de dados

- O serviço `db` usa a imagem oficial do Postgres e persiste dados em volume Docker.
- Configurações de acesso estão em `docker-compose.yml` e `application-docker.properties`.
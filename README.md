## 🍔 SaborHub - Tech Challenge Fase 2

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

Na nossa região, um grupo de restaurantes decidiu contratar estudantes para construir um sistema de gestão para seus estabelecimentos. Essa decisão foi motivada pelo alto custo de sistemas individuais, o que levou os restaurantes a se unirem para desenvolver um sistema único e compartilhado. Esse sistema permitirá que os clientes escolham restaurantes com base na comida oferecida, em vez de se basearem na qualidade do sistema de gestão.

O objetivo é criar um sistema robusto que permita a todos os restaurantes gerenciar eficientemente suas operações, enquanto os clientes poderão consultar informações, deixar avaliações e fazer pedidos online. Devido à limitação de recursos financeiros, foi acordado que a entrega do sistema será realizada em fases, garantindo que cada etapa seja desenvolvida de forma cuidadosa e eficaz.

A divisão em fases possibilitará uma implementação gradual e controlada, permitindo ajustes e melhorias contínuas conforme o sistema for sendo utilizado e avaliado pelos restaurantes e clientes.

### Objetivo do Projeto
O projeto tem como objetivo criar um sistema de gestão compartilhado para atender às necessidades dos restaurantes e de seus clientes.
O sistema contará com três tipos de usuários — administrador, dono de restaurante e cliente — cada um com informações como nome, e-mail, login, senha, data da última alteração e endereço.
A solução foi desenvolvida para execução em ambiente Docker, utilizando Docker Compose e um banco de dados relacional PostgreSQL, garantindo escalabilidade e facilidade de implantação.

Vídeo de apresentação do projeto: https://www.youtube.com/watch?v=BAVArNslJ78

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.4
- PostgreSQL 17
- Docker & Docker Compose
- Spring Boot DevTools (hot reload)
- Flyway (migração e versionamento do banco de dados)
- Spring Security (autenticação e autorização)
- JWT (JSON Web Tokens para autenticação)
- Lombok (redução de código boilerplate)
- Hibernate (ORM)
- Hibernate Types (suporte JSON/JSONB PostgreSQL)

## Configuração de Autenticação

### Variáveis de Ambiente:
O sistema utiliza as seguintes variáveis de ambiente para configuração:

- `JWT_SECRET`: Chave secreta para assinatura dos tokens JWT (padrão: "my-secret-key")
- `DATABASE_URL`: URL de conexão com o PostgreSQL
- `DATABASE_USERNAME`: Usuário do banco de dados
- `DATABASE_PASSWORD`: Senha do banco de dados

### Usando a API com Autenticação:

1. **Fazer Login**:
   ```bash
   POST /autenticacao/entrar
   {
     "login": "seu_login",
     "senha": "sua_senha"
   }
   ```

2. **Usar o Token**: Inclua o token retornado no header Authorization:
   ```
   Authorization: Bearer <seu_token_jwt>
   ```

3. **Fazer Logout**:
   ```bash
   POST /autenticacao/sair
   Authorization: Bearer <seu_token_jwt>
   ```

## Executando em Modo Desenvolvimento com Docker Compose

Para rodar a aplicação utilizando Docker, siga os passos abaixo:

1. Suba os containers
	```sh
	docker compose up
	```
 
2. Acesse a aplicação
- Por padrão, a aplicação ficará disponível em:
`http://localhost:8080`

## Executando em Modo Desenvolvimento Local (sem Docker)

1. Instale e configure o PostgreSQL 17 na porta 5432
- Certifique-se de criar o banco de dados e usuário conforme o arquivo application.properties.

2. Execute a aplicação
- Abra o projeto na sua IDE de preferência.
- Rode o método main da classe SaborhubApplication.

4. Acesse a aplicação
- Por padrão, a aplicação ficará disponível em:
`http://localhost:8080`

## Endpoints

### Autenticação:
- `POST /autenticacao/entrar` - Para um usuário fazer login e obter um token de autenticação.
- `POST /autenticacao/sair` - Para invalidar o token JWT e fazer logout.

### Gerenciamento de Usuários:
- `GET /usuario` - Lista todos os usuários cadastrados.
- `POST /usuario` - Cria um novo usuário.
- `GET /usuario/{id}` - Busca um usuário específico pelo seu ID. (Apenas ADMIN)
- `PUT /usuario` - Atualiza os dados de um usuário autenticado.
- `PUT /usuario/alterar-senha` - Altera a senha de um usuário autenticado.
- `DELETE /usuario/{id}` - Remove um usuário pelo seu ID. (Apenas ADMIN)

### Gerenciamento de Restaurantes:
- `GET /restaurante` - Lista todos os restaurantes cadastrados.
- `POST /restaurante` - Cadastra um novo restaurante. (DONO_RESTAURANTE ou ADMIN)
- `GET /restaurante/{id}` - Busca um restaurante específico pelo seu ID. (Apenas ADMIN)
- `PUT /restaurante/{id}` - Atualiza os dados de um restaurante. (DONO_RESTAURANTE ou ADMIN)
- `DELETE /restaurante/{id}` - Remove um restaurante pelo seu ID. (Apenas ADMIN)

### Gerenciamento de Cardápio:
- `GET /restaurante/{restauranteId}/cardapio` - Lista todos os itens do cardápio de um restaurante.
- `POST /restaurante/{restauranteId}/cardapio` - Adiciona um novo item ao cardápio. (DONO_RESTAURANTE ou ADMIN)
- `PUT /restaurante/{restauranteId}/cardapio/{itemId}` - Atualiza um item do cardápio. (DONO_RESTAURANTE ou ADMIN)
- `DELETE /restaurante/{restauranteId}/cardapio/{itemId}` - Remove um item do cardápio. (DONO_RESTAURANTE ou ADMIN)

## Sistema de Autorização

O sistema implementa um controle de acesso baseado em roles (perfis de usuário) utilizando Spring Security e JWT:

### Perfis de Usuário:
- **ADMIN**: Acesso total ao sistema, pode gerenciar usuários, restaurantes e cardápios
- **DONO_RESTAURANTE**: Pode cadastrar e gerenciar seus próprios restaurantes e cardápios
- **CLIENTE**: Acesso para visualizar restaurantes e cardápios (funcionalidades futuras)

### Autenticação:
- Baseada em JWT (JSON Web Tokens)
- Tokens têm prazo de validade configurável
- Sistema de logout que invalida tokens
- Middleware de segurança para verificação automática de tokens

### Autorização:
- Endpoints protegidos por anotações `@PreAuthorize`
- Verificação automática se o usuário tem permissão para acessar recursos
- Donos de restaurantes só podem modificar seus próprios estabelecimentos
- Administradores têm acesso irrestrito

## Estrutura do Banco de Dados

O sistema utiliza PostgreSQL com as seguintes entidades principais:

- **Usuario**: Informações dos usuários (nome, email, login, senha, perfil, endereço)
- **Restaurante**: Dados dos restaurantes (CNPJ, nome, endereço, tipo de cozinha, horário de funcionamento)
- **ItemCardapio**: Itens do cardápio (nome, descrição, preço, disponibilidade, foto)
- **Endereco**: Endereços completos (logradouro, número, complemento, bairro, cidade, estado, CEP)

## Arquitetura do Projeto

O projeto segue os princípios da **Arquitetura Limpa (Clean Architecture)** e está organizado em camadas bem definidas:

### Estrutura de Pastas:
```
src/main/java/com/saborhub/
├── application/           # Camada de Aplicação
│   ├── dto/              # Data Transfer Objects
│   ├── gateways/         # Interfaces dos repositórios
│   └── usecases/         # Casos de uso (regras de negócio)
├── domain/               # Camada de Domínio
│   ├── entities/         # Entidades de negócio
│   ├── enums/            # Enumerações
│   └── exceptions/       # Exceções de domínio
└── infra/                # Camada de Infraestrutura
    ├── config/           # Configurações (Security, JWT, etc.)
    ├── controller/       # Controllers REST
    ├── gateways/         # Implementações dos repositórios
    ├── persistence/      # Entidades JPA
    └── repository/       # Interfaces JPA Repository
```

### Principais Benefícios:
- **Baixo Acoplamento**: Camadas independentes e bem definidas
- **Alta Coesão**: Responsabilidades claramente separadas
- **Testabilidade**: Facilita criação de testes unitários e de integração
- **Manutenibilidade**: Código organizado e fácil de manter
- **Flexibilidade**: Facilita mudanças e evolução do sistema

## Documentação da API

### Collection para Teste

A melhor forma de testar a API é utilizando a Collection do Postman disponível no repositório:

**Link para a Collection do Postman:**
https://github.com/rodrigo-oliveira/saborhub/blob/main/SaborHub%20-%20Fluxos%20de%20Uso.postman_collection.json

**Como usar:**
1. Importe a collection no Postman
2. Clique com o botão direito sobre ela 
3. Selecione a opção "Run" para executar os fluxos de uso da aplicação
4. A collection contém exemplos de todos os endpoints com payloads de exemplo

## Banco de dados

- O serviço `db` usa a imagem oficial do Postgres e persiste dados em volume Docker.
- Configurações de acesso estão em `docker-compose.yml` e `application-docker.properties`.
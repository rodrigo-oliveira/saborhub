CREATE TABLE IF NOT EXISTS perfis (
    id   integer PRIMARY KEY,
    nome text NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios
(
    id                    TEXT PRIMARY KEY,
    nome                  TEXT   NOT NULL,
    email                 TEXT   NOT NULL,
    login                 TEXT   NOT NULL UNIQUE,
    senha                 TEXT   NOT NULL,
    data_ultima_alteracao TIMESTAMPTZ,
    endereco              JSONB  NOT NULL,

    perfil                integer REFERENCES perfis(id),

    FOREIGN KEY (perfil) REFERENCES perfis (id)
);

CREATE TABLE restaurantes
(
    id                    TEXT PRIMARY KEY,
    cnpj                  VARCHAR(14) NOT NULL,
    nome                  VARCHAR(255) NOT NULL,
    rua          VARCHAR(255),
    numero       VARCHAR(50),
    complemento  VARCHAR(255),
    bairro       VARCHAR(255),
    cidade       VARCHAR(255),
    estado       VARCHAR(100),
    cep          VARCHAR(20),
    tipo_cozinha          VARCHAR(100),
    horario_funcionamento VARCHAR(100),

    dono_id               TEXT         NOT NULL,

    FOREIGN KEY (dono_id) REFERENCES usuarios (id)
);

CREATE TABLE itens_cardapio
(
    id                          TEXT PRIMARY KEY,
    nome                        VARCHAR(255)   NOT NULL,
    descricao                   VARCHAR(500),
    preco                       DECIMAL(10, 2) NOT NULL,
    disponivel_somente_no_local BOOLEAN        NOT NULL,
    caminho_foto                VARCHAR(500),

    restaurante_id              TEXT         NOT NULL,

    FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id)
);

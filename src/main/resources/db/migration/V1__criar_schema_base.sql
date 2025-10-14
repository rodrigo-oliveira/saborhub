
CREATE EXTENSION IF NOT EXISTS pgcrypto;

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
    id                    BIGINT PRIMARY KEY,
    nome                  VARCHAR(255) NOT NULL,
    endereco              VARCHAR(500) NOT NULL,
    tipo_cozinha          VARCHAR(100),
    horario_funcionamento VARCHAR(100),

    dono_id               TEXT         NOT NULL,

    FOREIGN KEY (dono_id) REFERENCES usuarios (id)
);

CREATE TABLE itens_cardapio
(
    id                          BIGINT PRIMARY KEY,
    nome                        VARCHAR(255)   NOT NULL,
    descricao                   VARCHAR(500),
    preco                       DECIMAL(10, 2) NOT NULL,
    disponivel_somente_no_local BOOLEAN        NOT NULL,
    caminho_foto                VARCHAR(500),

    restaurante_id              BIGINT         NOT NULL,

    FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id)
);

CREATE TABLE usuarios (
    id TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    data_ultima_alteracao TIMESTAMPTZ,
    role SMALLINT NOT NULL,
    endereco JSONB NOT NULL
);
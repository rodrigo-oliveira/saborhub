CREATE TABLE usuarios (
    id TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    login TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    data_ultima_alteracao TIMESTAMPTZ,
    role TEXT NOT NULL CHECK (role IN ('ADMIN','CLIENTE','RESTAURANTE')),
    endereco JSONB NOT NULL
);
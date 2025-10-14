CREATE
    EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO perfis (id, nome)
VALUES (0, 'ADMINISTRADOR'),
       (1, 'DONO_RESTAURANTE'),
       (2, 'CLIENTE');

-- ADICIONA UM USUÁRIO ADMINISTRADOR PADRÃO
INSERT INTO usuarios (id, nome, email, login, senha, data_ultima_alteracao, perfil, endereco)
SELECT gen_random_uuid()::text,
       '${admin_nome}',
       '${admin_email}',
       '${admin_login}',
       crypt('${admin_senha}', gen_salt('bf')),
       now(),
       0,
       '{
         "rua": "Av. Admin",
         "numero": "0",
         "cidade": "Sabor City",
         "estado": "SC",
         "cep": "00000-000"
       }'::jsonb
WHERE NOT EXISTS (SELECT 1
                  FROM usuarios
                  WHERE login = '${admin_login}');
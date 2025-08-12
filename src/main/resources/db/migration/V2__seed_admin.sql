-- Enable pgcrypto for gen_random_uuid() and crypt()/gen_salt('bf') (bcrypt)
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Seed default ADMIN if login does not exist
INSERT INTO usuarios (id, nome, email, login, senha, data_ultima_alteracao, role, endereco)
SELECT gen_random_uuid()::text,
       '${admin_nome}',
       '${admin_email}',
       '${admin_login}',
       crypt('${admin_senha}', gen_salt('bf')),
       now(),
       'ADMIN',
       '{"rua":"Av. Admin","numero":"0","cidade":"Sabor City","estado":"SC","cep":"00000-000"}'::jsonb
WHERE NOT EXISTS (
  SELECT 1 FROM usuarios WHERE login = '${admin_login}'
);

INSERT INTO USUARIO(nome, email, senha) VALUES('Condomino', 'condomino@email.com', '$2y$12$Q6QDiSG6p58UEdlldrygEeDqKLUNW4uuI6jfxyQqC2c1GP1marP9e');
INSERT INTO USUARIO(nome, email, senha) VALUES('Sindico', 'sindico@email.com', '$2y$12$Q6QDiSG6p58UEdlldrygEeDqKLUNW4uuI6jfxyQqC2c1GP1marP9e');

INSERT INTO CONDOMINIO(nome, endereco) VALUES ('Condomínio das Flores', 'Rua das Flores, 252');
INSERT INTO CONDOMINIO(nome, endereco) VALUES ('Condomínio das Rosas', 'Rua das Rosas, 525');

INSERT INTO PERFIL(id, nome) VALUES (1, 'ROLE_SINDICO');
INSERT INTO PERFIL(id, nome) VALUES (2, 'ROLE_CONDOMINO');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES (1, 2);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES (2, 1);


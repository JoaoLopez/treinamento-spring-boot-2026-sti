-- Estas primiras linhas configuram o HSQLDB para trabalhar com schemas (databases) e simular o mysql
SET DATABASE SQL SYNTAX MYS TRUE;

DROP SCHEMA AP4 IF EXISTS CASCADE;

CREATE SCHEMA AP4 AUTHORIZATION DBA;

SET INITIAL SCHEMA AP4;
SET DATABASE DEFAULT INITIAL SCHEMA AP4;

-- ATENÇÃO: para usar nomes case-insentitive, precisa desabilitar um recurso do spring-data-jdbc.

CREATE TABLE ap4.usuario(
    id              BIGINT          NOT NULL AUTO_INCREMENT,
    username        VARCHAR(50)     NOT NULL,
    nome            VARCHAR(200)    NOT NULL,
    idade           INT             NOT NULL,
    url_imagem      VARCHAR(1000)   NOT NULL,
    CONSTRAINT PK_USUARIO          PRIMARY KEY (id)
);

CREATE TABLE ap4.post(
     id              BIGINT          NOT NULL AUTO_INCREMENT,
     data_postagem   DATETIME        NOT NULL,
     mensagem        VARCHAR(2200)   NOT NULL,
     usuario_id      BIGINT          NOT NULL,
     CONSTRAINT PK_POST              PRIMARY KEY (id),
     CONSTRAINT PK_POST_USUARIO_ID   FOREIGN KEY (usuario_id) REFERENCES ap4.usuario(id)
)

CREATE TABLE ap4.post_tag(
     post_id           BIGINT          NOT NULL,
     nome              VARCHAR(255)    NOT NULL,
     ordem             INT             NOT NULL DEFAULT 0,
     CONSTRAINT PK_POST_TAG    PRIMARY KEY (post_id, nome),
     CONSTRAINT UK_POST_TAG_POST   FOREIGN KEY (post_id) REFERENCES ap4.post(id)
);



-- 1. Inserindo 5 Usuários
INSERT INTO ap4.usuario (id, username, nome, idade, url_imagem) VALUES
                                                                    (1, 'pixel_art_fan', 'Carlos Silva', 14, 'https://example.com/img/carlos.jpg'),
                                                                    (2, 'rpg_quest', 'Ana Batista', 15, 'https://example.com/img/ana.png'),
                                                                    (3, 'combo_master', 'Bruno Souza', 14, 'https://example.com/img/bruno.webp'),
                                                                    (4, 'fazenda_virtual', 'João Pedro', 16, 'https://example.com/img/joao.jpg'),
                                                                    (5, 'speed_run_pro', 'Mariana Costa', 15, 'https://example.com/img/mariana.png');
(30, 'Suspense', 0), (30, 'Sobrevivência', 1);

-- Usuário 1: Foco em Plataforma
INSERT INTO ap4.post (id, data_postagem, mensagem, usuario_id) VALUES
                                                                   (1, '2020-03-15', 'O level design de Super Mario World é genial. É incrível como um jogo de tantos anos ainda é tão divertido!', 1),
                                                                   (2, '2021-06-22', 'Donkey Kong Country tem uma das melhores trilhas sonoras que eu já ouvi. A vibe da floresta é demais!', 1),
                                                                   (3, '2022-01-10', 'Zerei Sonic 2 hoje! A fase da Chemical Plant é muito rápida, mal consegui piscar os olhos.', 1),
                                                                   (4, '2023-09-05', 'Castlevania: Symphony of the Night tem um mapa gigante. Adoro descobrir passagens secretas nas paredes.', 1),
                                                                   (5, '2024-11-20', 'Tentei passar do Mega Man X sem usar armadura extra, mas o desafio é bem grande. Vou treinar mais!', 1),
                                                                   (6, '2026-02-14', 'Streets of Rage 2 é o melhor para jogar com os amigos. O estilo artístico dos anos 90 é muito estiloso.', 1);

-- Usuário 2: Foco em RPGs
INSERT INTO ap4.post (id, data_postagem, mensagem, usuario_id) VALUES
                                                                   (7, '2020-05-10', 'Chrono Trigger é um dos melhores RPGs da história. Viajar no tempo para salvar o futuro é uma ideia incrível.', 2),
                                                                   (8, '2021-02-18', 'O vilão do Final Fantasy VI é muito marcante. Ele realmente consegue mudar o mundo inteiro no meio do jogo.', 2),
                                                                   (9, '2022-07-30', 'Earthbound tem um humor muito diferente e legal. É raro ver um RPG que se passa nos tempos modernos.', 2),
                                                                   (10, '2023-12-01', 'O sistema de magias do Final Fantasy VII é muito bem feito. Dá para customizar os personagens de vários jeitos.', 2),
                                                                   (11, '2024-04-12', 'Secret of Mana é muito divertido para jogar em dupla. Os gráficos coloridos do SNES são lindos.', 2),
                                                                   (12, '2026-01-20', 'Escolhi o Charmander no Pokémon Red. É um clássico que nunca perde a graça, mesmo sendo em preto e branco.', 2);

-- Usuário 3: Foco em Luta e Arcade
INSERT INTO ap4.post (id, data_postagem, mensagem, usuario_id) VALUES
                                                                   (13, '2020-01-05', 'Imagina como era legal a época dos fliperamas! Jogar Street Fighter II com a galera devia ser sensacional.', 3),
                                                                   (14, '2021-08-09', 'Finalmente aprendi a fazer todos os golpes especiais no controle. Exige bastante coordenação!', 3),
                                                                   (15, '2022-03-15', 'Mortal Kombat 3 tem personagens muito icônicos. O visual digitalizado dos atores era bem avançado para a época.', 3),
                                                                   (16, '2023-10-22', 'Metal Slug tem as animações mais detalhadas que eu já vi em pixel art. Tudo se mexe na tela!', 3),
                                                                   (17, '2024-05-05', 'The King of Fighters 97 tem times de personagens muito equilibrados. Gosto muito do estilo do Terry Bogard.', 3),
                                                                   (18, '2025-12-10', 'Tartarugas Ninja: Turtles in Time é diversão garantida. É muito legal jogar as fases baseadas em diferentes épocas.', 3);

-- Usuário 4: Foco em Simulação e Estratégia
INSERT INTO ap4.post (id, data_postagem, mensagem, usuario_id) VALUES
                                                                   (19, '2020-11-02', 'Harvest Moon do SNES é muito relaxante. Cuidar da fazenda e dos animais é um ótimo passatempo.', 4),
                                                                   (20, '2021-09-14', 'Age of Empires II é pura estratégia. Você precisa equilibrar a economia e o exército ao mesmo tempo.', 4),
                                                                   (21, '2022-05-20', 'Construir uma cidade inteira no SimCity 2000 é um desafio e tanto. O planejamento urbano é muito importante.', 4),
                                                                   (22, '2023-08-08', 'Harvest Moon 64 tem um charme especial. Os festivais da cidade são os momentos mais legais do jogo.', 4),
                                                                   (23, '2024-10-31', 'RollerCoaster Tycoon permite criar os parques de diversão mais malucos de todos. É muito divertido ver os visitantes.', 4),
                                                                   (24, '2026-03-01', 'Jogos de simulação antigos mostram como é bom planejar cada passo da nossa jornada virtual.', 4);


-- Usuário 5: Foco em Aventura 3D
INSERT INTO ap4.post (id, data_postagem, mensagem, usuario_id) VALUES
                                                                   (25, '2020-08-25', 'Super Mario 64 foi o primeiro jogo que joguei em 3D. A liberdade de movimento é incrível até hoje.', 5),
                                                                   (26, '2021-11-11', 'The Legend of Zelda: Ocarina of Time tem uma história épica. Atravessar o campo de Hyrule no cavalo é demais!', 5),
                                                                   (27, '2022-09-02', 'Super Metroid tem uma atmosfera de mistério muito boa. Explorar o planeta Zebes dá uma sensação de aventura real.', 5),
                                                                   (28, '2023-04-18', 'Doom clássico é muito rápido! É impressionante como eles conseguiram fazer um jogo assim nos computadores antigos.', 5),
                                                                   (29, '2024-02-28', 'GoldenEye 007 é o rei do multiplayer de sofá. Disputar partidas com os amigos no N64 é inesquecível.', 5),
                                                                   (30, '2025-05-09', 'Resident Evil 2 original é puro suspense. Você precisa economizar cada item para conseguir sobreviver.', 5);

-- 3. Inserindo as Tags (Até 5 por post)
INSERT INTO ap4.post_tag (post_id, nome, ordem) VALUES
                                                    (1, 'Plataforma', 0), (1, 'Nintendo', 1), (1, 'Retrô', 2),
                                                    (2, 'Trilha Sonora', 0), (2, 'SNES', 1),
                                                    (3, 'Sonic', 0), (3, 'Velocidade', 1), (3, 'Sega', 2),
                                                    (4, 'Aventura', 0), (4, 'Exploração', 1), (4, 'Clássico', 2),
                                                    (5, 'Ação', 0), (5, 'Desafio', 1),
                                                    (6, 'Luta de Rua', 0), (6, 'Multiplayer', 1), (6, 'Anos 90', 2),
                                                    (7, 'RPG', 0), (7, 'História', 1), (7, 'Clássico', 2),
                                                    (8, 'RPG', 0), (8, 'Vilão', 1),
                                                    (9, 'RPG', 0), (9, 'Divertido', 1),
                                                    (10, 'Customização', 0), (10, 'Fantasia', 1),
                                                    (11, 'Cooperativo', 0), (11, 'RPG de Ação', 1),
                                                    (12, 'Coleção', 0), (12, 'Pokémon', 1), (12, 'Portátil', 2),
                                                    (13, 'Luta', 0), (13, 'Arcade', 1), (13, 'Fliperama', 2),
                                                    (14, 'Habilidade', 0), (14, 'Luta', 1),
                                                    (15, 'Luta', 0), (15, 'Visual', 1),
                                                    (16, 'Tiro', 0), (16, 'PixelArt', 1), (16, 'Arcade', 2),
                                                    (17, 'Luta', 0), (17, 'Estratégia', 1),
                                                    (18, 'Beat em up', 0), (18, 'Nostalgia', 1),
                                                    (19, 'Fazenda', 0), (19, 'Simulador', 1),
                                                    (20, 'Estratégia', 0), (20, 'História', 1), (20, 'PC', 2),
                                                    (21, 'Cidades', 0), (21, 'Simulador', 1),
                                                    (22, 'Fazenda', 0), (22, 'N64', 1),
                                                    (23, 'Parque de Diversão', 0), (23, 'Criatividade', 1),
                                                    (24, 'Simulação', 0), (24, 'Planejamento', 1),
                                                    (25, 'Mundo 3D', 0), (25, 'Plataforma', 1), (25, 'Nintendo', 2),
                                                    (26, 'Épico', 0), (26, 'Zelda', 1), (26, 'Exploração', 2),
                                                    (27, 'Aventura', 0), (27, 'Ficção Científica', 1),
                                                    (28, 'Tiro em 1ª Pessoa', 0), (28, 'Clássico PC', 1),
                                                    (29, 'Multiplayer', 0), (29, 'Ação', 1), (29, 'Competitivo', 2),
                                                    (30, 'Suspense', 0), (30, 'Sobrevivência', 1);
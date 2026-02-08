-- 1. Cidade
CREATE TABLE cidade (
    id_cidade SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- 2. Tecnico
CREATE TABLE tecnico (
    id_tecnico SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- 3. Time
CREATE TABLE Time_futebol (
    id_time SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    caminho_escudo VARCHAR(255),
    id_cidade INT NOT NULL,
    id_tecnico INT UNIQUE, 
    FOREIGN KEY (id_cidade) REFERENCES cidade(id_cidade),
    FOREIGN KEY (id_tecnico) REFERENCES tecnico(id_tecnico)
);

-- 4. Jogador
CREATE TABLE jogador (
    id_jogador SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    n_camisa INT,
    posicao VARCHAR(50),
    caminho_foto VARCHAR(255), 
    id_time INT NOT NULL,
   
    FOREIGN KEY (id_time) REFERENCES Time_futebol(id_time) ON DELETE CASCADE
);

-- 5. Campeonato
CREATE TABLE campeonato (
    id_campeonato SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- 6. Tabela Associativa
CREATE TABLE time_campeonato (
    id_time INT,
    id_campeonato INT,
    PRIMARY KEY (id_time, id_campeonato),
    FOREIGN KEY (id_time) REFERENCES Time_futebol(id_time),
    FOREIGN KEY (id_campeonato) REFERENCES campeonato(id_campeonato)
);
DROP TABLE IF EXISTS POKEMON;
CREATE TABLE IF NOT EXISTS POKEMON (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(255),
    nombre VARCHAR(255),
    altura VARCHAR(255),
    peso VARCHAR(255)
);

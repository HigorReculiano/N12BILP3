CREATE DATABASE IF NOT EXISTS ftt ;

use ftt;

CREATE TABLE IF NOT EXISTS company (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    cnpj varchar(255) UNIQUE, 
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role (
	id int NOT NULL AUTO_INCREMENT,
	description varchar(255),
	
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS  employer (
    id int NOT NULL AUTO_INCREMENT,
    company_id int not null,
    role_id int not null,
    name varchar(255) NOT NULL,
    age int,
    login varchar(255) not null unique,
    
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO role (description )
VALUES 
('Admin'),
('Manager'),
('Employer');

INSERT INTO company (name , cnpj)
VALUES
('Globo', '27.865.757/0001-02'),
('FTT', '59.107.300/0001-17'),
('Toledo', '59.704.510/0021-36'),
('Gorila', '14.053.036/0001-48');

INSERT INTO employer (name , login , age , company_id , role_id )
VALUES
('Christian', 'chseki', 27, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Higor', 'higorh', 20, (SELECT id FROM company WHERE name = 'Toledo'), (SELECT id FROM role WHERE description = 'Employer')),
('Vitor', 'vitorgc', 19, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Cristina', 'chtina', 27, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Thamires', 'thami', 20, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Vittorina', 'vittorina', 19, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Renatinha', 'rnata', 31, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Renato', 'Renatinho', 27, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Cacaroto', 'cribon', 17, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Vegeta', 'vegeta', 27, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),

('Rafael', 'rafs', 21, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Rafaella', 'rafinha', 18, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Daniel', 'danis', 42, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Lamesa', 'table', 30, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Faustão', 'fausto', 49, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Serginho', 'sergs', 75, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),

('Guilherme', 'guish', 30, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Manager')),
('Juliete', 'julbbb', 27, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Manager')),
('Leonardo', 'leojh', 32, (SELECT id FROM company WHERE name = 'Toledo'), (SELECT id FROM role WHERE description = 'Manager')),
('Vu', 'Who', 127, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Manager'));

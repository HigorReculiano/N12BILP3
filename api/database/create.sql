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

INSERT INTO employer (name , age , company_id , role_id )
VALUES
('Christian', 27, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Higor',  20, (SELECT id FROM company WHERE name = 'Toledo'), (SELECT id FROM role WHERE description = 'Employer')),
('Vitor',  19, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Cristina',  27, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Thamires',  20, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Vittorina',  19, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Renatinha',  31, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Renato',  27, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Cacaroto',  17, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Vegeta',  27, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),

('Rafael',  21, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Rafaella',  18, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Employer')),
('Daniel',  42, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Lamesa',  30, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Employer')),
('Faustão',  49, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),
('Serginho', 75, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Employer')),

('Guilherme', 30, (SELECT id FROM company WHERE name = 'Gorila'), (SELECT id FROM role WHERE description = 'Manager')),
('Juliete',  27, (SELECT id FROM company WHERE name = 'Globo'), (SELECT id FROM role WHERE description = 'Manager')),
('Leonardo',  32, (SELECT id FROM company WHERE name = 'Toledo'), (SELECT id FROM role WHERE description = 'Manager')),
('Vu',  127, (SELECT id FROM company WHERE name = 'FTT'), (SELECT id FROM role WHERE description = 'Manager'));

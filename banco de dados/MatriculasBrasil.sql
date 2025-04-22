CREATE TABLE MatriculasBrasil (
	id SERIAL PRIMARY KEY,
	estado VARCHAR(200) NOT NULL,
	cidade VARCHAR(200) NOT NULL,
	ies VARCHAR(200) NOT NULL,
	sigla VARCHAR(50),
	organizacao VARCHAR(200) NOT NULL,
	categoria_administrativa VARCHAR(200) NOT NULL,
	nome_do_curso VARCHAR(200) NOT NULL,
	nome_detalhado_do_curso VARCHAR(200) NOT NULL,
	modalidade VARCHAR(200) NOT NULL,
	grau VARCHAR(50),
	matriculas_2014 INT,
	matriculas_2015 INT,
	matriculas_2016 INT,
	matriculas_2017 INT,
	matriculas_2018 INT,
	matriculas_2019 INT,
	matriculas_2020 INT,
	matriculas_2021 INT,
	matriculas_2022 INT
);


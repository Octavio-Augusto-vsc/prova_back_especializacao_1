CREATE TABLE movie (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 nome varchar(100) NOT NULL,
 id_genero bigint(20) NOT NULL,
 url_stream varchar(100) DEFAULT NULL,
 porta varchar(100),
 PRIMARY KEY (id)
);
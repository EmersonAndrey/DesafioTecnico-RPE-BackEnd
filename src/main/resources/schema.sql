CREATE TABLE cliente( 
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    nome VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL, 
    status_bloqueio ENUM('A', 'B') NOT NULL, 
    limite_credito FLOAT NOT NULL
);
    
CREATE TABLE fatura(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor FLOAT NOT NULL,
    status ENUM('P', 'A', 'B') NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
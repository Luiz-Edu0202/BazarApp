CREATE DATABASE IF NOT EXISTS bazar_db;
USE bazar_db;

-- Tabela: OrgaoFiscalizador
CREATE TABLE IF NOT EXISTS orgao_fiscalizador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    senha VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabela: OrgaoDonatario
CREATE TABLE IF NOT EXISTS orgao_donatario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(50),
    horario_funcionamento VARCHAR(100),
    descricao TEXT,
    senha VARCHAR(255) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabela: Lote
CREATE TABLE IF NOT EXISTS lote (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_entrega DATE NOT NULL,
    observacao TEXT,
    orgao_fiscalizador_id INT NOT NULL,
    orgao_donatario_id INT NOT NULL,
    CONSTRAINT fk_lote_fiscalizador FOREIGN KEY (orgao_fiscalizador_id) REFERENCES orgao_fiscalizador(id),
    CONSTRAINT fk_lote_donatario FOREIGN KEY (orgao_donatario_id) REFERENCES orgao_donatario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabela: Produto
CREATE TABLE IF NOT EXISTS produto (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    lote_id INT NOT NULL,
    CONSTRAINT fk_produto_lote FOREIGN KEY (lote_id) REFERENCES lote(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- Inserindo Órgãos Fiscalizadores
INSERT INTO orgao_fiscalizador (nome, descricao, senha) VALUES 
('Polícia Federal', 'Órgão de fiscalização federal', '123456'),
('Receita Federal', 'Órgão de arrecadação e fiscalização', '123456');

-- Inserindo Órgãos Donatários
INSERT INTO orgao_donatario (nome, endereco, telefone, horario_funcionamento, descricao, senha) VALUES 
('APAE', 'Rua das Flores, 123', '81 99999-1111', '08:00 às 17:00', 'Associação de Pais e Amigos dos Excepcionais', '123456'),
('IMIP', 'Av. Central, 456', '81 99999-2222', '08:00 às 18:00', 'Instituto de Medicina Integral Prof. Fernando Figueira', '123456');

-- Inserindo Lotes (Amarrando Fiscalizador e Donatário)
-- Lote 1: PF doou para a APAE
-- Lote 2: Receita Federal doou para o IMIP
-- Lote 3: PF doou para o IMIP
INSERT INTO lote (data_entrega, observacao, orgao_fiscalizador_id, orgao_donatario_id) VALUES 
('2023-10-15', 'Lote de roupas e calçados apreendidos.', 1, 1),
('2023-11-20', 'Lote de equipamentos hospitalares apreendidos.', 2, 2),
('2023-12-01', 'Lote de brinquedos.', 1, 2);

-- Inserindo Produtos (Amarrando ao Lote)
-- Produtos do Lote 1 (APAE)
INSERT INTO produto (nome, descricao, lote_id) VALUES 
('Camisa Polo', 'Camisa polo azul tamanho M', 1),
('Tênis Esportivo', 'Tênis branco número 42', 1),
('Calça Jeans', 'Calça jeans escura tamanho 40', 1);

-- Produtos do Lote 2 (IMIP)
INSERT INTO produto (nome, descricao, lote_id) VALUES 
('Monitor Cardíaco', 'Monitor cardíaco marca X, funcionando', 2),
('Cadeira de Rodas', 'Cadeira de rodas dobrável', 2);

-- Produtos do Lote 3 (IMIP)
INSERT INTO produto (nome, descricao, lote_id) VALUES 
('Boneca', 'Boneca de pano', 3),
('Carrinho de Controle Remoto', 'Carrinho vermelho', 3);
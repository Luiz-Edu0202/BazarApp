

# Bazar App - Sistema de Gestão de Doações

Sistema web desenvolvido para intermediar a doação de lotes de produtos entre Órgãos Fiscalizadores (ex: Polícia Federal, Receita Federal) e Órgãos Donatários (ex: APAE, IMIP).

## 📌 Funcionalidades

- **Autenticação de Usuários:** Login e controle de sessão para os dois tipos de órgãos.
- **Painel do Fiscalizador:** Permite cadastrar novos lotes e visualizar apenas os lotes que ele mesmo criou.
- **Cadastro de Lotes:** Permite inserir a data de entrega, observações, selecionar o órgão donatário e adicionar múltiplos produtos ao lote.
- **Painel do Donatário:** Permite visualizar todos os lotes que foram doados especificamente para ele.
- **Detalhes do Lote:** Tela compartilhada que lista todos os produtos que compõem um lote específico.

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3** (Spring Web MVC)
- **Thymeleaf** (Template Engine)
- **Bootstrap 5** (Estilização via CDN)
- **MySQL** (Banco de Dados)
- **JDBC Puro** (Sem uso de JPA/Hibernate ou JdbcTemplate, conforme requisito do projeto)

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:
- **Model/Entity:** Classes de representação dos dados (Lote, Produto, Órgãos).
- **Repository:** Camada de persistência construída com SQL puro e `PreparedStatement` nativo do Java (`java.sql`). Utiliza o `DataSource` do Spring para gerenciar conexões e transações.
- **Service:** Camada responsável pelas regras de negócio (ex: validar login, injetar o fiscalizador logado no lote).
- **Controller:** Camada de roteamento HTTP, manipulação de sessões (`HttpSession`) e envio de dados para as views.
- **View:** Páginas HTML (Thymeleaf).

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- JDK 17 ou superior.
- Maven instalado.
- Servidor MySQL rodando localmente (ou em container).

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Luiz-Edu0202/BazarApp.git
   cd BazarApp

2. **Configure o Banco de Dados:**
    - Crie um banco de dados chamado `bazar_db` no seu MySQL.
    - Execute o script SQL (disponível na pasta `src/main/resources/schema.sql` e `data.sql` ou no final deste documento) para criar as tabelas e popular os dados iniciais.

3. **Variáveis de Ambiente:**
   O projeto espera as credenciais do banco via variáveis de ambiente. Na sua IDE (IntelliJ, Eclipse, etc), configure:
    - `DB_USER`: Seu usuário do MySQL (ex: root)
    - `DB_SENHA`: Sua senha do MySQL

   *Ou altere diretamente o arquivo `src/main/resources/application.properties` substituindo `${DB_USER}` e `${DB_SENHA}` pelas suas credenciais.*

4. **Execute a aplicação:**
   Rode a classe `BazarAppApplication.java` na sua IDE.
   Acesse no navegador: `http://localhost:8080`

## 🔑 Credenciais de Teste (Dados Iniciais)

Se você rodou o script de população do banco (`data.sql`), pode usar os seguintes acessos:

**Órgão Fiscalizador:**
- Nome: `Polícia Federal` | Senha: `123456`
- Nome: `Receita Federal` | Senha: `123456`

**Órgão Donatário:**
- Nome: `APAE` | Senha: `123456`
- Nome: `IMIP` | Senha: `123456`


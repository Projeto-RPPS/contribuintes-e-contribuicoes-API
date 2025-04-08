# RPPS API

Esta é a API REST desenvolvida para o sistema de gerenciamento de contribuintes de um regime próprio de previdência social (RPPS). Ela permite o cadastro, atualização e consulta de contribuintes, categorias, contribuições, parentescos, salários mínimos e estrutura genealógica (filiações), além da documentação acessível via Swagger.

---

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3+
- PostgreSQL
- JDBC
- Flyway
- Swagger (Springdoc)
- Docker e Docker Compose
- Postman (para testes)

---

## 📁 Estrutura do Projeto

```
rpps-project/
├── .env                     # Arquivo de variáveis de ambiente
├── docker-compose.yml       # Arquivo de configuração do Docker
├── README.md
├── rppsProject/             # Projeto Java (src/main/java)
│   └── ...
```

---

## 🚀 Como Executar o Projeto

### 1. Pré-requisitos

- Docker instalado
- Java 17+ e Maven (caso deseje rodar localmente sem container)

---

### 2. Configuração do Ambiente

Crie um arquivo `.env` na raiz do projeto com as variáveis:

```env
DB_URL=jdbc:postgresql://localhost:5433/postgres
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha

PGADMIN_DEFAULT_EMAIL=admin@rpps.com
PGADMIN_DEFAULT_PASSWORD=admin123
```

---

### 3. Subir o Banco de Dados + PGAdmin

Na raiz do projeto:

```bash
docker-compose up -d
```

Containers gerados:

- `rpps_db` (PostgreSQL - porta 5433)
- `pgadmin4` (interface web - http://localhost:8081)

---

### 4. Conectar no PGAdmin

- Acesse `http://localhost:8081`
- Login: conforme `.env`
- Novo servidor:
  - Host: `rpps_db`
  - Porta: `5432`
  - Usuário/senha do `.env`

---

### 5. Rodar a API

Com Docker pronto e banco no ar:

```bash
./mvnw spring-boot:run
```

Ou rode pela IDE (IntelliJ, VSCode...)

---

## 📄 Documentação

Acesse a doc Swagger:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 Testes

- Postman ou Swagger
- Endpoints já validados com `@Valid` e `ResponseEntity`

---

## 👤 Autor

João Pedro Varela Borges — projeto acadêmico focado em sistemas previdenciários com Java e PostgreSQL.
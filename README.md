
# RPPS API

API REST para o gerenciamento de contribuintes de um Regime Próprio de Previdência Social (RPPS). O sistema permite cadastro, atualização, consulta e controle de contribuintes, contribuições, categorias, vínculos familiares e histórico de salário mínimo.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.4.4
- PostgreSQL
- JDBC + Flyway
- Swagger (Springdoc OpenAPI)
- Docker e Docker Compose

---

## ⚙️ Variáveis de Ambiente (.env)

```env
DB_URL=jdbc:postgresql://postgres:5432/rppsdb
DB_USERNAME=myuser
DB_PASSWORD=mypassword
DB_NAME=rppsdb
APP_NAME=rppsProject

PGADMIN_DEFAULT_EMAIL=admin@example.com
PGADMIN_DEFAULT_PASSWORD=adminpass

SERVER_PORT=8084
```

---

## 🐳 Como Executar via Docker

### 0. Criar rede para utilizar o docker-compose

```bash
 docker network create shared_network
```

### 1. Subir os serviços

```bash
docker-compose up -d --build
```

**Serviços incluídos**:

- `rpps_db` — PostgreSQL (porta `5432`)
- `pgadmin4` — Interface para gerenciar o banco (`http://localhost:8081`)
- `rpps_api` — API do projeto rodando em `http://localhost:8084`

---

### 2. Acessar Swagger

Com a aplicação rodando, acesse:

```
http://localhost:8084/swagger-ui.html
```

---

### 3. Login no PGAdmin

- **URL**: http://localhost:8081
- **Email**: admin@example.com
- **Senha**: adminpass

Servidor:

- **Host**: `rpps_db`
- **Porta**: `5432`
- **Usuário**: `myuser`
- **Senha**: `mypassword`

---

## 🧪 Testes e Validações

- Testes podem ser realizados via Postman ou diretamente pelo Swagger.
- As validações de entrada usam `@Valid` com mensagens personalizadas.
- Retorno de erros é tratado por um `@ControllerAdvice`.

---

## 👤 Autor

João Pedro Varela Borges — Projeto acadêmico utilizando Spring Boot e PostgreSQL com foco em sistemas previdenciários municipais.

---

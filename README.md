# 💼 Desafio Técnico RPE – API de Clientes e Faturas

API RESTful desenvolvida para gerenciar clientes e suas respectivas faturas. O sistema permite registrar, listar, bloquear clientes inadimplentes e realizar pagamentos de faturas, com regras de negócio automáticas via agendamento (scheduler).

---

## 🧱 Tecnologias Utilizadas

🔹 **Backend**
 - ☕ Java 17  
 - 🧩 Spring Boot

🔹 **DataBase**
 - 💾 MySQL

🔹 **Ambiente/infraestrutura(opcional)**
 - 🐳 Docker
   
---

## Requisitos

- Java 17
- Maven
- MySQL 8 
- Docker e Docker Compose(opcional)

---

## 🚀 Como Executar o Projeto Localmente

### 📁 1. Clonar o Repositório

```bash
git clone https://github.com/EmersonAndrey/DesafioTecnico-RPE-BackEnd.git
cd DesafioTecnico-RPE-BackEnd
```

### 🛠️ 2. Configurar Banco de Dados MySQL

Crie um banco chamado `rpeteste` no MySQL.

Edite o arquivo `src/main/resources/application.yml` com suas credenciais:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/rpeteste
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: seu_usuario
    password: sua_senha
```

> 📝 Substitua `seu_usuario` e `sua_senha` pelos dados reais.

---

### 📥 3. Instalar Dependências

```bash
mvn clean install
```

---

### ▶️ 4. Executar a Aplicação

Via Maven:

```bash
./mvnw spring-boot:run
```

Ou via Docker:

```bash
docker-compose up --build
```

---

### 🌐 Acesse a API:

- Swagger UI: http://localhost:8080/swagger-ui.html
  
---

## 🔁 Regras de Negócio Implementadas

- Ao registrar um pagamento, o status da fatura muda para `Paga`.
- Se o pagamento não for feito até 3 dias após o vencimento, o cliente é marcado como `Bloqueado`.
- Clientes `Bloqueados` têm seu limite de crédito atualizado para R$ 0,00.
- Essa verificação ocorre automaticamente todo dia às 12:00h via `@Scheduled`.

---

## ✅ Funcionalidades
- Clientes:
    - Criar e listar
    - Buscar por ID
    - Atualizar/Bloquear
- Faturas:
    - Listar faturas de um cliente
    - Listar faturas em atraso
    - Registrar pagamento
- Scheduler diário para verificação de bloqueio
- Documentação automática da API com Swagger

---

## 🧪 Testes

- Testes unitários com JUnit para:
  - ClienteService
  - FaturaService
  - ClienteMapper e FaturaMapper
  - Regras de bloqueio
 
Para rodar os testes:
```bash
mvn test
```

---

## 🔌 Endpoints Principais

Use preferencialmente o **frontend** para interagir com a API.  
Também é possível testar com ferramentas como Postman ou Insomnia.


### 🔐 Cliente

| Método | Endpoint                        | Descrição                  |
|--------|---------------------------------|----------------------------|
| GET    | `api/clientes`                  | Lista todos os clientes    |
| POST   | `api/clientes`                  | Cadastra novo cliente      |
| GET    | `api/clientes/{id}`             | Consulta cliente por ID    |
| PUT    | `api/clientes/{id}`             | Atualiza/bloqueia cliente  |
| GET    | `api/clientes/bloqueados`       | Lista clientes bloqueados  |

### Exemplo de requisição(POST `api/clientes`)

```json
{
 "nome": "Emerson",
 "cpf": "03937142096",
 "dataNascimento": "2003-11-27",
 "statusBloqueio": "A",
 "limiteCredito": 5000
}
```

### 🔐 Fatura

| Método | Endpoint                        | Descrição                               |
|--------|---------------------------------|-----------------------------------------|
| GET    | `api/faturas/{id}`              | Lista todas as faturas de um cliente    |
| PUT    | `api/faturas/{id}/pagamento`    | Registra pagamento para uma fatura      |
| GET    | `api/faturas/atrasadas`         |  Lista faturas em atraso                |

---

## 💡 Melhorias Futuras
Caso houvesse mais tempo, as seguintes melhorias poderiam ser implementadas:

- Implementar autenticação com Spring Security e JWT
- Criar um endpoint de relatório com faturas vencidas por período

---

## 👨‍💻 Desenvolvedor

**Emerson Andrey Fausto dos Santos**  

# 📚 Bookstore Manager API

API RESTful para gerenciamento de livros, desenvolvida com Spring Boot e PostgreSQL via Docker.
Inclui operações CRUD completas e pode ser facilmente testada com o Postman.

---

## ✅ Tecnologias utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker + Docker Compose
- Lombok
- MapStruct
- Postman

---

## 🚀 Como executar o projeto

### ✅ Pré-requisitos
- Docker
- Docker Compose
- Java 17+
- Maven 3.8+

### ✅ Passos:

```bash
# 1. Clone o repositório:
git clone https://github.com/seu-usuario/bookstore-manager.git

# 2. Vá até o repositório do projeto:
cd bookstore-manager

# 3. Suba o PostgreSQL com Docker Compose:
docker compose up -d
```

### ✅ Configure o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstoremanager
spring.datasource.username=bookstore_user
spring.datasource.password=bookstore_pass
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### ✅ Compile e execute

```bash
mvnw spring-boot:run
```

OU

## ✅ Como rodar via IntelliJ IDEA

1. Abra o **IntelliJ IDEA**.
2. Vá em **File > Open** e selecione a pasta raiz do projeto.
3. Aguarde o IntelliJ importar as dependências Maven automaticamente.
4. Configure o PostgreSQL rodando via Docker conforme instruções anteriores.
5. No painel lateral, abra a classe principal com `@SpringBootApplication` (ex.: `BookstoreManagerApplication.java`).
6. Clique no ícone verde ▶️ ao lado do método `main` para executar a aplicação.

---

## ✅ A API estará disponível em:

[http://localhost:8080/api/v1/books](http://localhost:8080/api/v1/books)

---

## ✅ Endpoints da API

| Método | Endpoint               | Descrição                  |
|-------- |---------------------- |---------------------------|
| **POST**   | `/api/v1/books`         | Cadastra um novo livro     |
| **GET**    | `/api/v1/books`         | Lista todos os livros      |
| **GET**    | `/api/v1/books/{id}`    | Busca um livro por ID      |
| **PUT**    | `/api/v1/books/{id}`    | Atualiza um livro          |
| **DELETE** | `/api/v1/books/{id}`    | Deleta um livro            |

---

## ✅ Estrutura do BookDTO

### 1️⃣ Criando livro com **ID de autor já cadastrado**:

```json
{
  "nome": "Livro 1",
  "paginas": 200,
  "capitulos": 5,
  "isbn": "0-596-52968-9",
  "nomeEditora": "Editora 1",
  "autor": {
    "id": 1
  }
}
```

### 2️⃣ Criando livro com **criação automática de autor**:

```json
{
  "nome": "Livro 1",
  "paginas": 200,
  "capitulos": 5,
  "isbn": "0-596-52968-9",
  "nomeEditora": "Editora 1",
  "autor": {
    "nome": "Gustavo",
    "idade": 20
  }
}
```

---

## ✅ Testando com o Postman

### ➡️ **POST** `/api/v1/books`
- Método: POST
- URL: `http://localhost:8080/api/v1/books`
- Body: JSON conforme exemplos acima

### ➡️ **GET** `/api/v1/books`
- Método: GET
- URL: `http://localhost:8080/api/v1/books`

### ➡️ **GET** `/api/v1/books/{id}`
- Método: GET
- URL: `http://localhost:8080/api/v1/books/1`

### ➡️ **PUT** `/api/v1/books/{id}`
- Método: PUT
- URL: `http://localhost:8080/api/v1/books/1`
- Body: JSON conforme exemplos acima

### ➡️ **DELETE** `/api/v1/books/{id}`
- Método: DELETE
- URL: `http://localhost:8080/api/v1/books/1`

---

## ✅ Exceções tratadas
- `BookNotFoundException`
- `NoBooksFoundException`
- `AutorNotFoundException`

---

## ✅ Banco de Dados

- PostgreSQL rodando via Docker.
- Volume persistente `pgdata` criado automaticamente.
- Consultável via pgAdmin, DBeaver ou psql.

### ✅ Como parar a aplicação:
#### Para encerrar o BD: ####
```bash
docker compose down
```
    
#### Para fechar a aplicação: ####

##### Caso rodando via terminal: ##### 
 ```bash
 Cntrl + C (2x)
```
     
##### Caso rodando via IntelliJ: #####

Clique no quadrado vermelho ⏹️ na barra inferior ou no console do Run.

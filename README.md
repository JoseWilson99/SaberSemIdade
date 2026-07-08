# 🚀 Back-end - Saber sem Idade

API REST desenvolvida com **Java**, **Spring Boot** e **MySQL**, responsável pelo gerenciamento de usuários, cursos e matrículas da plataforma **Saber sem Idade**.

## Tecnologias

- Java
- Spring Boot
- Maven
- MySQL
- Spring Data JPA
- Hibernate

---

# Endpoints da API

## 👥 Usuários (`/api/usuarios`)

### GET `/api/usuarios`

Lista todos os usuários cadastrados.

**Resposta (200 OK)**

```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "senhaCriptografadaOuTexto",
    "dataCadastro": "2026-07-08T14:30:00.000+00:00"
  }
]
```

---

### GET `/api/usuarios/{id}`

Busca um usuário pelo ID.

**Resposta (200 OK)**

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senhaCriptografadaOuTexto",
  "dataCadastro": "2026-07-08T14:30:00.000+00:00"
}
```

**Erro**

```
404 Not Found
```

---

### POST `/api/usuarios`

Realiza o cadastro de um novo usuário.

**Requisição**

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senhaCriptografadaOuTexto"
}
```

**Resposta (200 OK)**

Retorna o usuário criado com **id** e **dataCadastro**.

---

### POST `/api/usuarios/login`

Realiza o login do usuário.

**Requisição**

```json
{
  "email": "joao@email.com",
  "senha": "senhaCriptografadaOuTexto"
}
```

**Resposta (200 OK)**

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senhaCriptografadaOuTexto",
  "dataCadastro": "2026-07-08T14:30:00.000+00:00"
}
```

**Erro (401 Unauthorized)**

```
E-mail ou senha inválidos.
```

---

### PUT `/api/usuarios/{id}`

Atualiza um usuário.

**Requisição**

```json
{
  "nome": "João Silva Editado",
  "email": "joao.novo@email.com",
  "senha": "novaSenhaAlterada"
}
```

**Resposta (200 OK)**

Retorna o usuário atualizado.

---

### DELETE `/api/usuarios/{id}`

Remove um usuário.

**Resposta (200 OK)**

```
Usuário excluído com sucesso.
```

---

# 📚 Cursos (`/api/cursos`)

### GET `/api/cursos`

Lista todos os cursos.

**Resposta**

```json
[
  {
    "id": 1,
    "titulo": "Inclusão Digital para Seniores",
    "descricao": "Curso prático focado no uso do smartphone e redes sociais.",
    "dataCriacao": "2026-06-01T10:00:00.000+00:00"
  }
]
```

---

### GET `/api/cursos/{id}`

Busca um curso pelo ID.

```json
{
  "id": 1,
  "titulo": "Inclusão Digital para Seniores",
  "descricao": "Curso prático focado no uso do smartphone e redes sociais.",
  "dataCriacao": "2026-06-01T10:00:00.000+00:00"
}
```

---

### POST `/api/cursos`

Cadastra um novo curso.

**Requisição**

```json
{
  "titulo": "Introdução ao Computador",
  "descricao": "Aprenda a navegar na internet e organizar arquivos."
}
```

---

### PUT `/api/cursos/{id}`

Atualiza um curso.

**Requisição**

```json
{
  "titulo": "Introdução ao Computador e Internet",
  "descricao": "Aprenda a utilizar computadores e navegar com segurança."
}
```

---

### DELETE `/api/cursos/{id}`

Exclui um curso.

**Resposta**

```
204 No Content
```

---

# 📝 Matrículas (`/api/matriculas`)

### GET `/api/matriculas`

Lista todas as matrículas.

**Resposta**

```json
[
  {
    "id": 1,
    "usuario": {
      "id": 1,
      "nome": "João Silva"
    },
    "curso": {
      "id": 1,
      "titulo": "Inclusão Digital para Seniores"
    },
    "dataMatricula": "2026-07-08T16:45:12.000+00:00",
    "status": "ATIVO"
  }
]
```

---

### GET `/api/matriculas/{id}`

Busca uma matrícula pelo ID.

---

### GET `/api/matriculas/usuario/{usuarioId}`

Lista todas as matrículas de um usuário específico.

---

### POST `/api/matriculas`

Realiza uma matrícula.

**Requisição**

```json
{
  "usuario": {
    "id": 1
  },
  "curso": {
    "id": 1
  },
  "status": "ATIVO"
}
```

**Erro (500 Internal Server Error)**

```
Erro ao criar matrícula.
```

---

### PUT `/api/matriculas/{id}`

Atualiza o status da matrícula.

**Requisição**

```json
{
  "status": "CONCLUIDO"
}
```

---

### DELETE `/api/matriculas/{id}`

Remove uma matrícula.

**Resposta**

```
204 No Content
```

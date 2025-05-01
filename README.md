# 🎓 Projeto Matrículas

> Universidade de Caxias do Sul  
> Autor: João Paulo  
> Data: 30/04/2025  

---

## 📌 Objetivo

Desenvolver uma aplicação com uma arquitetura que adere a um dos padrões arquiteturais discutidos: **MVC** ou **MVVM**.

---

## 🧰 Tecnologias Utilizadas

- **Frontend**: HTML, CSS, JavaScript  
- **Backend (API)**: Java, Spring Boot  
- **SGBD**: PostgreSQL  
- **Armazenamento Local**: `localStorage` (nativo do navegador)

---

## 🏗️ Estrutura da Aplicação

A aplicação foi desenvolvida com o framework **Spring Boot**, utilizado para criar APIs REST de forma rápida e com configuração mínima.

A camada de **View (visualização)** utiliza HTML, CSS e JavaScript puro, rodando no navegador. Ela interage unicamente com a **Controller da API** por meio de chamadas HTTP (`fetch`), consumindo os endpoints REST do backend.

---

## 📂 Componentes da Aplicação

### 🔸 View
- `index.html` – Estrutura da página  
- `index.js` – Lógica de requisições e exibição  
- `index.css` – Estilização visual

### 🔸 Model
- `Matricula.java` – Representa a entidade `matriculasbrasil` no banco

### 🔸 DAO (Data Access Object)
- `IMatriculaDAO.java` – Interface JPA que acessa o banco com métodos automáticos via `JpaRepository`

### 🔸 Repository
- `MatriculasRepository.java` – Media a comunicação entre `Service` e `DAO`

### 🔸 Service
- `MatriculasService.java` – Contém a lógica de negócio, processamento e agregações

### 🔸 Controller
- `MatriculaController.java` – Exposição dos endpoints REST para consumo externo

---

## 🌐 Endpoints REST

| Método | Endpoint                          | Descrição                                                                 |
|--------|-----------------------------------|---------------------------------------------------------------------------|
| GET    | `/matriculas`                    | Lista paginada de todas as matrículas                                    |
| GET    | `/matriculas/total-por-ano`      | Total de alunos por ano, com filtro opcional de modalidade               |
| GET    | `/matriculas/ranking2022`        | Top 10 cursos com mais matrículas em 2022, opcional por modalidade       |
| GET    | `/matriculas/total-por-estado`   | Total por ano filtrado por estado e modalidade                           |
| GET    | `/matriculas/ranking2022PorEstado` | Ranking por estado e modalidade                                        |
| GET    | `/matriculas/estados`            | Lista única de estados disponíveis

---

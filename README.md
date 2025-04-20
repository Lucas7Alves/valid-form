# Formulário com Validação

<div align="left">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5">
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
</div>

## 📋 Sobre o Projeto (em construção...)

Este projeto é um formulário de cadastro de usuários com validação de dados no **frontend (JavaScript)** e no **backend (Spring Boot com JPA e PostgreSQL)**, reforçando práticas de segurança e integridade das informações fornecidas.

O formulário verifica:
- Nome com no mínimo 3 caracteres
- E-mail válido
- Senha com:
  - Entre 8 e 20 caracteres
  - Pelo menos um número
  - Pelo menos uma letra maiúscula

As validações são realizadas tanto no **JavaScript**, quanto no **back-end** com regras de negócio aplicadas em uma API REST desenvolvida em **Java + Spring Boot**.

---

## ✅ Tecnologias Utilizadas

- HTML5
- CSS3
- JavaScript
- Java 17+
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- PostgreSQL
- Maven
- Docker (opcional)
- H2 Database (para testes locais)

---

## ⚙️ Requisitos

- Java JDK 17 ou superior
- Maven
- PostgreSQL ou H2
- Navegador Web
- [Postman](https://www.postman.com/) (opcional, para testes da API)

---

## 🚀 Executando o projeto

### Backend (Spring Boot)
1. Clone o repositório:
   ```bash
   git clone https://github.com/Lucas7Alves/valid-form.git
   cd valid-form/barrier

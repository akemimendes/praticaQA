Prática QA – API com Aplicação de Plano de Testes
📖 Sobre o Projeto

Este projeto consiste no desenvolvimento de uma API REST utilizando Java + Spring Boot, com foco na aplicação prática de um Plano de Testes estruturado, validando funcionalidades, regras de negócio e tratamento de exceções.

O objetivo principal é demonstrar a implementação de testes unitários, testes de integração e validações de regras conforme definido no plano de testes do projeto.

🎯 Objetivo

Aplicar um plano de testes completo em uma API de cadastro de usuários, garantindo:

✔️ Validação de campos obrigatórios

✔️ Verificação de e-mail duplicado

✔️ Tratamento global de exceções

✔️ Testes unitários de service

✔️ Testes de controller

✔️ Testes de integração

🛠️ Tecnologias Utilizadas

Java 17+

Spring Boot

Spring Data JPA

Maven

JUnit 5

Mockito

MySQL

Postman (para testes manuais)

🏗️ Estrutura do Projeto
src/
 ├── main/
 │   ├── controller
 │   ├── service
 │   ├── repository
 │   ├── model
 │   └── exception
 └── test/
     ├── unit
     └── integration

🔎 Funcionalidades Implementadas
👤 Usuário

Cadastro de usuário

Validação de campos obrigatórios

Validação de e-mail único

Tratamento de erros personalizados

🧪 Plano de Testes Aplicado

O projeto contempla:

✔️ Testes Unitários

Service

Controller (com mock)

Validações de regras de negócio

✔️ Testes de Integração

Fluxo completo da API

Teste de endpoints reais

Validação de status HTTP

✔️ Testes de Exceções

Campo obrigatório

E-mail já existente

Tratamento global de erros

▶️ Como Executar o Projeto
1️⃣ Clonar o repositório
git clone <URL_DO_REPOSITORIO>

2️⃣ Entrar na pasta
cd praticaQA

3️⃣ Rodar a aplicação
./mvnw spring-boot:run


Ou no Windows:

mvnw.cmd spring-boot:run

▶️ Executar os Testes
./mvnw test

📊 Cobertura de Testes

O projeto busca garantir alta cobertura das regras de negócio principais, especialmente:

Validações

Fluxos de erro

Retornos HTTP

Regras de unicidade

📌 Boas Práticas Aplicadas

Separação de camadas (Controller, Service, Repository)

Tratamento global de exceções

Testes isolados com Mockito

Testes de integração com contexto real

Código organizado e de fácil manutenção

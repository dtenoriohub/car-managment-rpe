# Car managment RPE

Projeto utilizado no processo seletivo para vaga de Estagiário Desenvolvedor back-End.

## Visão geral

O projeto é uma aplicação back-end com objetivo de desenvolver uma API com uma função CRUD capaz de manter o cadastro dos veiculos de uma empresa.

## Tecnologias
---
* Banco de dados  H2.
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/guides/index.html)
* [Spring](https://spring.io/)
    * Spring Boot
    * Spring Data JPA
    * Spring Web
* [Swagger](https://swagger.io/)
* JUnit5
* Mockito
* MockMVC

-------------------------

# Setup da aplicação (local)
- IDE usada no desenvolvimento: [Intellij](https://www.jetbrains.com/pt-br/idea/)
-------------------------
## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 17
Maven 3.3.3 
```
-------------------------
## Preparando ambiente
Foi optado pela utilização de um banco de dados H2 em memória pela praticidade da construção e execução dos testes.

-------------------------
## Instalação e execução da aplicação

* Primeiramente, faça o clone do repositório:
```
https://github.com/dtenoriohub/car-managment-rpe.git
```
* Feito isso, é recomendado acessar o projeto com a IDE [Intellij](https://www.jetbrains.com/pt-br/idea/)
* use o botão de "Run" para realizar a execução da aplicação, ou pressione o atalho Shift+F10.

-------------------------

## Acesso ao Swagger

Para ter acesso ao Swagger, é necessário em um navegador acessar o seguinte link:

[http://localhost:8080/swagger-ui/index.html#/]

ao acessar a interface do Swagger, ficará disponivel os EndPoints da aplicação, com as operações de CRUD disponiveis, tanto para os veiculos de passeio - Passenger Cars, quanto para os veiculos de carga - Cargo Cars


-------------------------
## O que foi feito na aplicação
    * CRUD para as entidades solicitadas
    * Banco relacional H2
    * Testes Usando JUnit5
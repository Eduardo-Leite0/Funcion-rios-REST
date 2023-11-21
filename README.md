# Funcionarios-REST
## Descrição

Durante o sexto período do curso de Engenharia da Computação na Universidade São Francisco, elaboramos este projeto na disciplina de Programação Orientada a Objetos, sob a orientação do Prof. José Matias Lemes Filho. O objetivo principal do projeto é criar um sistema de registro de funcionários, aplicando os conceitos fundamentais da Programação Orientada a Objetos.

## Objetivo do Projeto

O objetivo central deste projeto é criar um sistema de cadastro de funcionários, proporcionando operações básicas de criação, leitura, atualização e exclusão (CRUD). O sistema visa armazenar informações como nome, cargo e salário dos funcionários, contribuindo para uma gestão eficiente dos recursos humanos da empresa.

## Atriubutos Funcionários:
- Nome;
- Sobrenome;
- CPF;
- Data de Nascimento;
  - Idade;
- Ramal;
- Telefone de Emergencia;
- Cargo;
- Tempo de contrato;
- Ativo.
  
## Cadastro de Funcionários:

### Método: POST
URL: http://localhost:8080/Funcionarios
Corpo da requisição (JSON):
- Raw
- json
Copy code
{
  "nome": "João",
  "sobrenome": "leuri",
  "cpf": "123.456.789-09",
  "dataNascimento": "2000-01-01",
  "ramal": "3122-1234",
  "telefoneEmergencia": "9999-9999",
  "cargo": "Desenvolvedor",
  "tempoContrato": 12
}
Listagem de Funcionários:

### Método: GET
URL: http://localhost:8080/funcionarios

Detalhes de um Funcionário por ID:
URL: http://localhost:8080/funcionarios/{id}
Atualização de Informações de um Funcionário:

### Método: PUT
URL: http://localhost:8080/funcionarios/{id}
Corpo da requisição (JSON):
- Raw
- json
Copy code
{
  "nome": "João",
  "sobrenome": "ricardo",
  "cpf": "111.426.789-09",
  "dataNascimento": "2000-01-01",
  "ramal": "3122-1134",
  "telefoneEmergencia": "0000-9999",
  "cargo": "Auxiliar",
  "tempoContrato": 15,
  "ativo":true
}
### Método: DELETE
URL: http://localhost:8080/funcionarios/{id}
Tecnologias Utilizadas

## Aplicações utilizadas:
- Java 17 
- Spring Tools 4 v3.1.5 
- Postman v10.20 

Este projeto visa aplicar os conceitos aprendidos em Programação Orientada a Objetos na criação de um sistema prático e funcional para gerenciamento de funcionários em uma empresa fictícia.

### GIT
https://github.com/Eduardo-Leite0/Funcionarios-REST.git

### Autor:
Eduardo de Souza Leite RA:202125512

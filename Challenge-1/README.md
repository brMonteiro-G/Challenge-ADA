# Iniciando projeto

# Hello World: 

Olá,separei a evolução do código em alguns pacotes diferentes, cada um com uma versão do código a medida que foram se desenvolvendo as etapas do projeto. 
Esse primeiro pacote contém a primeira versão da api. 

Challenge 1: CRUD de Pessoa Física e Pessoa Jurídica

Débitos técnicos para serem revisitados em outros momentos: 

## Troca do banco de dados.
- Os benefícios do h2 são muito pequenos em relação ao uso de outros bancos de dados e outras arquiteturas, por ser um banco em memória não será possível escalar a aplicação. 
    ### Possíveis soluções 
    - banco de dados dockerizado rodando em uma instância EC2 na Amazon, com escabailidade vertical. Ao optar por escala horizontal poderíamos ter perda de integridade. 
    - banco de dados gerenciado como RDS da AWS 

## Adicionar abstração para devolução dos dados de request.
- Será necessário adicionar futuramente uma camada de DTO para esconder a modelagem feita no banco (verificar pacote challenge-4)

## Testes unitários 

- Adicionar testes unitários para validar cenários onde o usuário mande um tamnho de cpf e cnpj maior do que o esperado. (verificar pacote challenge-4)
- Nem todas as classes serão testadas, já que classes de modelagem não necessitam de testes, sendo mais interessante focar os esforços em classes de regra de negócio. 

## Tratamento de exceptions

- Aqui o tratamento de exceções deve ser mais granular, investigando os mínimos detalhes de casos de erro, nessa versão foram cobertos erros de validação lançados pelo spring-validation 
- porém ainda existe oportunidade para outros tratamentos de erro. 


# Stack
- java 17 
- spring 3.0.11

# Como rodar? 
Basta baixar o projeto e rodar localmente. 
```bash
git pull origin master
cd ADA-CHALLENGE/Challenge-1
```

# Swagger
```bash
localhost:8082/swagger-ui-custom.html
```
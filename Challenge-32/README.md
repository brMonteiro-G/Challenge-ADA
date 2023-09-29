# Iniciando projeto

- Primeira decisão é a modelagem, optei por iniciar criando um CRUD para cada uma das entidades em questão e coloquei o
  lombok no projeto para reduzir a quantidade de código boilerplate
- A opção pelo uso do Banco de dados em memória h2 vem no sentido de ser o banco de dados mais habitual e o visto em
  aula, dessa forma, dado o prazo apertado como MVP, seria interessante partir pelo caminho mais fácil no primeiro
  momento, ficando como débito técnico ao final do processo, já que o h2 não seria suficiente para ambientes produtivos
- iniciando CRUD -> 19:52
- implementando a dependência do modelMapper, com o objetivo de facilitar a conversão entre DTOS e DAOS. -> 20:18
- Pausa de 30-35 minutos para comer
- iniciando a camada de validação dos dados que entram na api
-
    - pensei em utilizar uma anotação customizada mas no momento acredito que seja mais simples apenas uma regra de
      negócio que adicione zeros nos campos
- posteriormente retirar uso de biblioteca para não pesar no tamanho do artefato -> commons-lang
- débito técnico, reduzir duplicidade de código das entidades Legal Person e natural Person
- testes devem ser feitos majoritariamente encima de regras de negócio, portanto, DTOS e classes de modelagem não
  precisam ser testadas, bem como métodos de libs externas.
- testes são uma balança entre esforço e ganho, no caso o maior ganho com menor esforço ao momento é testar apenas
  classes com regras de negocio
- debito tecnico, identifiquei a necessidade de melhorar os tratamentos de erro da api
- revisitar ajuste na anotação @Max
- debito tecnico adicionar generics a Queue criada, para restringir apenas ao tipo LegalPerson or NaturalPerson
- adicionar camada de ResponseDTO para customizar a devolução 
# Iniciando projeto

# Hello World:

Olá,separei a evolução do código em alguns pacotes diferentes, cada um com uma versão do código a medida que foram se desenvolvendo as etapas do projeto.
Esse primeiro pacote contém a segunda versão da api.

Challenge 3: Integração com SQS e SNS

Débitos técnicos para serem revisitados em outros momentos:

## Implementação de padrão DLQ 

- O padrão Dead Letter Queue recomenda a criação de um novo fila com um período de retenção que minimize a perda de mensagens
em caso de erro no momento do consumo da mensagem na fila. 

## Código Dinâmico
- Com essa versão do código precisamos setar manualmente no arquivo application.properties alguns valores para que a 
  aplicação consiga se conectar com os serviços da AWS, futuramente será interessante adicionar uma camada de utilização do aws sdk 
  que permite buscar as urls, arns e outros de maneira automática baseado em minhas credenciais e regiões criadas com base no cloudformation.


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
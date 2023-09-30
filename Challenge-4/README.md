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

## Tratamento de exceptions 

- adicionar tratamento de exceção customizado aos trechos de código com integração a serviços externos como a aws. 

## Script com AWS CLI 

- Seria interessante aqui também um script com a ferramente de cli da aws, com ela poderia ser feito a criação da stack por meio da linha de comando 
além de poder passar parâmetros de configuração aos recursos usados.

## Stack no S3
- Como boa prática é interessante manter o arquivo de IaC em um bucket do S3 ao invés de deixa-lo no projeto, assim nos referenciariamos ao endereço dele para criar os recursos.

# Stack
- java 17
- spring 3.0.11

# Como rodar?
1. Entre na AWS, no serviço Cloudformation
2. Crie uma stack e faça upload do arquivo presente no caminho ``./infrastructure/stack.yml``
3. Após criados os recursos, deveremos buscar as seguintes propriedades: 
```bash
spring.cloud.aws.region.static= regiao que foi criada a stack
spring.cloud.aws.credentials.access-key= access key gerada no IAM para cada usuário
spring.cloud.aws.credentials.secret-key= secret key gerada no IAM para cada usuário

aws.topic-arn = arn do topico do sns
aws.queue-name= nome da fila sqs 
spring.cloud.aws.sqs.endpoint=  url da fila sqs 
```

Adicione essas informações no seu arquivo `application.properties`
e basta rodar o projeto localmente 

```bash
git pull origin master
cd ADA-CHALLENGE/Challenge-1
```

# Swagger
```bash
localhost:8082/swagger-ui-custom.html
```





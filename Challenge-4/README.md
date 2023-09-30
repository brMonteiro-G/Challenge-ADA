# Iniciando projeto

# Hello World:

Olá,separei a evolução do código em alguns pacotes diferentes, cada um com uma versão do código a medida que foram se desenvolvendo as etapas do projeto.
Esse primeiro pacote contém a última versão da api.

Challenge 4: segurança da aplicação

## Problemas de segurança a serem analisados: 

 - Vulnerabilidades de dependências usadas no projeto 
 - Secret keys abertas no arquivo application.properties
 - A aplicação precisa de um token jwt para fazer autenticação.
 - Rotas que efetuam transações no banco e não possuem regras de roles para fazer autorização de usuários.
 - Nenhuma proteção contra ataques DDOS 
 - Nenhuma proteção contra CSRF 
 - Nenhuma proteção contra XSS e clickjacking

### Maiores criticidades: 

1. Falta de um token JWT 
2. Vulnerabilidade a ataques DDOS (AWS shield é uma solução) e CSRF 

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





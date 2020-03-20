# Octoevents API

Octo Events é uma aplicação que recebe e armazena eventos do Github, por intermédio de webhooks e os eventos são expostos por uma API.


| Requisito | Descrição | 
| ----------- | ---- |
| JAVA 8| Versão do java para executar o sistema | 
| Mysql |  Banco de Dados |  

## Executando a aplicação

Para executá-la você deve: 
1 - Ter Mysql instalado e criar um schema " testedb", que será a base de dados da aplicação
    - informar o usuário e a senha do mysql no arquivo properties ( \octoeventsteste\src\main\resources\application.properties)
2 - Ter o maven instalado e executar: 'mvn spring-boot:run' na raiz do projeto onde está o arquivo pom.xml
3 - Para testar o funcionamento do webhook em ambiente de desenvolvimento local, é necessário gerar uma url pública apontando para o localhost e configurar o Webhook, foi utilizado o _ngrok_ (https://ngrok.com/).  

### Criando o Webhook

1) No repositório do Github, ir em _Settings > Webhooks_  
2) Selecionar _Add webhook_  
3) Em _Payload URL_ incluir a URL da API para o Endpoint de Payload. No caso: _{base_url}/events_  
4) Definir _Content-Type_ como _application/json_ 

## Utilização

Estão disponíveis os endpoints:

| HTTP | Rota | Descrição |
| ----------- | ---- | --------- |
| **POST** | /events | Webhook que recebe eventos do Github e os armazena na Base de Dados |
| **GET** | /issues/{issue_number}/events | Listagem de eventos filtrados pelo número do issue |
| **GET** | /issues | Listagem de eventos no issue |


	

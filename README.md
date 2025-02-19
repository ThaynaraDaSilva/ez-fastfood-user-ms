# ez-fastfood-user-ms

## Contextualização
O "**ez-fastfood-users**" é um microsserviço responsável gestão de **clientes e funcionarios** da lanchonete que está em expansão. Ele adota a arquitetura limpa (clean architecture) e é implantado com AWS EKS, aproveitando os recursos de orquestração de contêineres para otimizar a disponilidade.

## Estrutura de diretórios do projeto
```
├── src/main/java
│   └── br.com.fiap.ez.fastfood
│       ├── adapters
│       │   ├── in.controller
│       │   │  
│       │   └── out
│       │       ├── repository
│       │       
│       │   
│       ├── application
│       │   ├── dto
│       │   └── usecases
│       ├── domain
│       │   ├── model
│       │   └── repository
│       ├── frameworks
│       │   ├── config
│       │   ├── exception
│       ├── infrastructure
│       │   ├── config
│       │   ├── mapper
│       │   ├── persistence
│       │   └── ApiApplication.java
│
├── src/main/resources
├── src/test/java
├── k8s
```
## Banco de Dados - MongoDB

![Image](https://github.com/user-attachments/assets/8e48f7b9-e33c-4f84-8ab8-ebf48f52b3eb)

Durante a segregação do monolito para microsserviços, optamos por um banco de dados NoSQL para o microsserviço user-ms. Essa escolha visa minimizar custos, reduzir retrabalho e proporcionar maior flexibilidade no armazenamento de perfis dinâmicos para diferentes tipos de usuários. Essa abordagem facilita futuras expansões e otimiza campanhas promocionais ao permitir uma personalização mais eficiente dos dados.


## Pré requisitos

Tecnologias utilizadas:

- Java 17
- Spring Boot 3.3.1
- Hibernate
- MongoDB
- DockerHub (https://hub.docker.com/repository/docker/dasilvathaynara/ez-fastfood-order-ms/general)
- Kubernetes
- OpenApi


### Como compilar o projeto (caso necessário)
```sh
git clone https://github.com/ThaynaraDaSilva/ez-fastfood-user-ms.git
cd ez-fastfood-user-ms
### Compilação local
mvn clean package -Pdev
## Compilação para publicação com K8s
### Desta forma o 'application.properties' terá parametros que serão injetados com valores no momento de subir os pods.
mvn clean package -Pprd
```

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>

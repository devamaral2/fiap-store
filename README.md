<h1 align="center"> Tech Challenge Fase 5 - Fiap Store </h1>
# Índice 

* [Desafio Proposto](#desafio-proposto)
* [Funcionalidades](#funcionalidades)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Integrantes do Grupo](#pessoas-desenvolvedoras)
* [Descrição do Projeto](#descrição-do-projeto)



Segue o projeto desenvolvido para a pós-graduação FIAP em Arquitetura e desenvolvimento Java.Desafio proposto:

Desenvolver um  sistema de eCommerce que permita aos usuários 
    - Cadastro de usuários (registro de campos como nome e email);
    - Login (autenticação e autorização de usuários cadastrados);
    - Carrinho de Compras(os usuários podem adicionar e remover itens do carrinho e o mesmo deve ser persistente associado ao usuário logado);
    - Gestão de Itens Administrador (os usuários administradores farão a gestão de itens de compra  controle de cadastro,manutenção e preços);
    - Pagamento(simulação de pagamento);
    

# Funcionalidades

TELA 1 - Login de usuário : Usuario precisa ter login e senha para realizar compras.
 
 Login: emailcadastrado@ggml.com 
 
 Senha:  *******

IMAGEM TELA1

TELA 2 - Cadastro de usuário : necessário realizar o registro do usuario , preenchendo os  Todos os campos obrigatórios.

  Nome: Usuario Teste
  
  Email: emailcadastrado@ggml.com
  
  Senha:********
  

IMAGEM TELA2


TELA 3 - Gestão de itens Administrador : gerenciar os produtos do site.

  Cadastro do Item : Preencher campos obrigatórios como nome ,imagem do produto , preço e quantidade de itens disponíveis no estoque; 

IMAGEM TELA3


TELA 4 - Produtos disponíveis e Carrinho de Compras.

  Selecionar os produtos escolhidos.
  

IMAGEM TELA4


TELA 5 - Pagamento. 

  Exibe os Produtos selecionados ;
  
  Preencha os dados da compra;
  


# Tecnologias Utilizadas
  - SpringBoot 
  - Java
  - Spring Security
  - Spring feign
  - Typescript
  - bun
  - next.js


# Integrantes 
 * Andre S Ferreira
 * Eduardo Vilhena
 * Giulliana Munhoz
 * Rafael Amaral
 * Tiago Santana
 


# Descrição dos Microserviços:


1. **Microserviço de Login** (porta 8001):
   - Gerencia o cadastro e login de usuários.
   - Verifica se o usuário tem permissão (autorização) para executar determinadas ações.
   - Usa o **Spring Security** para autenticação e autorização.
   - Armazena informações como: nome de usuário, senha e role (papel, ex: admin ou cliente).

2. **Microserviço de Produtos** (porta 8002):
   - Lista e gerencia produtos (nome, preço, estoque, etc.).
   - Verifica a disponibilidade de itens no estoque.
   - Autentica usuários e permite operações baseadas em seu papel.
   - Utiliza validações para garantir a integridade dos dados.

3. **Microserviço de Carrinho** (porta 8003):
   - Gerencia o estado do carrinho de compras entre as sessões.
   - Permite adicionar e remover produtos no carrinho.
   - Usa **Spring WebFlux** para permitir operações reativas e melhorar a performance em sistemas de alta demanda.

4. **Microserviço de Pagamento** (porta 8004):
   - Processa o pagamento dos itens no carrinho.
   - Confirma o pagamento e atualiza o estoque do produto.
   - Comunicação assíncrona entre o microserviço de carrinho e o de pagamento através do **RabbitMQ** (fila de mensagens).

# Para rodar o projeto
## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- **Docker e Docker Compose**: Para criar e gerenciar os containers de banco de dados.
- **Java JDK 21** ou superior: Para rodar os microserviços Spring Boot.
- **Node.js e bun**: Para rodar o frontend.
- **Maven**: Para rodar os serviços Spring Boot.

## Como Rodar o Projeto

### 1. Clone o repositório

Clone o repositório do projeto para sua máquina local:

```bash
git clone https://github.com/devamaral2/fiap-store
cd seu-projeto
```

### 2. Inicie os containers do backend

Na raiz do projeto, há um arquivo docker-compose.yml que configura os bancos de dados para os 4 microserviços. Para criar os containers dos bancos de dados com volumes persistentes, execute o comando:

```bash
docker-compose up -d
```

Esse comando criará e iniciará os containers dos microserviços e banco de dados para cada serviço. Caso queira subir os microserviços de forma individualizada existe um arquivo "docker-compose.yml" disponível em cada diretório dos microserviços.

Obs: Após você rodar o microserviço de produtos utilize o arquivo database.sql para adicionar os primeiros produtos da loja

### 3. Rodando o frontend

Após rodar os microserviços, navegue até a pasta do frontend e execute o projeto:

```bash
cd apps/frontend
bun install
bun run build
bun start
```
Caso você não deseje instalar o runtime bun você poderá utilizar o npm também 

```bash
cd apps/frontend
npm install
npm run build
npm start
```

Isso iniciará o frontend na porta 3000.
4. Acessando o projeto no navegador

Após os microserviços e o frontend estarem rodando, você poderá acessar o projeto via navegador:

```bash
http://localhost:3000
```


## Licença

Este projeto está licenciado sob a Licença MIT. Isso significa que você pode usá-lo, modificá-lo e distribuí-lo livremente, desde que mantenha o aviso de copyright original. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.





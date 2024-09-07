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
  
  Selecione o método de pagamento (pix ou cartão de crédito);
  

IMAGEM TELA5


# Tecnologias Utilizadas
  - SpringBoot
  - WebFlux 
  - Java
  - Spring Security
  - JavaScript


# Integrantes 
 * Andre S Ferreira
 * Eduardo Vilhena
 * Giulliana Munhoz
 * Rafael Amaral
 * Tiago Santana



# Descrição do Projeto 

![image](https://github.com/user-attachments/assets/0acc035d-1b6b-40d4-bef9-cbcd69b61193)

### Descrição dos Microserviços:

1. **Gateway** (porta 8000):
   - Roteia as requisições para os microserviços.
   - Exemplo: um usuário tenta acessar o carrinho de compras, o gateway identifica a rota e redireciona para o microserviço correto.

2. **Microserviço de Login** (porta 8001):
   - Gerencia o cadastro e login de usuários.
   - Verifica se o usuário tem permissão (autorização) para executar determinadas ações.
   - Usa o **Spring Security** para autenticação e autorização.
   - Armazena informações como: nome de usuário, senha e role (papel, ex: admin ou cliente).

3. **Microserviço de Produtos** (porta 8002):
   - Lista e gerencia produtos (nome, preço, estoque, etc.).
   - Verifica a disponibilidade de itens no estoque.
   - Autentica usuários e permite operações baseadas em seu papel.
   - Utiliza validações para garantir a integridade dos dados.

4. **Microserviço de Carrinho** (porta 8003):
   - Gerencia o estado do carrinho de compras entre as sessões.
   - Permite adicionar e remover produtos no carrinho.
   - Usa **Spring WebFlux** para permitir operações reativas e melhorar a performance em sistemas de alta demanda.

5. **Microserviço de Pagamento** (porta 8004):
   - Processa o pagamento dos itens no carrinho.
   - Confirma o pagamento e atualiza o estoque do produto.
   - Comunicação assíncrona entre o microserviço de carrinho e o de pagamento através do **RabbitMQ** (fila de mensagens).

6. **Mensageria com RabbitMQ**:
   - Utilizado para comunicação entre os microserviços, como o envio de mensagens entre o carrinho e o pagamento.

### Autenticação e Autorização:
- **Spring Security** garante que apenas usuários autorizados possam executar certas ações, como adicionar produtos ao carrinho ou realizar pagamentos.
- Sessões de usuário são mantidas, garantindo que o carrinho de compras não seja perdido após o usuário sair do sistema.

### Validação e Segurança:
- **Spring Secret** pode ser usado para proteger informações sensíveis.
- Validações de integridade, como a verificação de estoque e confirmação de pagamento, garantem a consistência dos dados.

Com este projeto conseguimos englobar e aplicar todos os conhecimentos obtidos durante a graduação.Concluímos apresentando este sistema escalável, seguro e otimizado para lidar com muitas requisições simultâneas graças ao uso de **WebFlux** e à arquitetura baseada em microserviços.





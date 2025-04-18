
⚠️ (EM FASE DE DESENVOLVIMENTO,não está totalmente funcional,apresentado alguns bugs)⚠️

## 🎮 E-commerce de Jogos

### 📖 Descrição do Projeto
Este projeto consiste em uma API REST para gerenciamento de um e-commerce de jogos, desenvolvida utilizando Java, Spring Boot, JPA Repository e banco de dados PostgreSQL. A API permite o gerenciamento de diversas entidades, oferecendo endpoints para operações CRUD (Create, Read, Update, Delete) sobre essas entidade.



## 🧱 Arquitetura e Padrões Utilizados

### 🧩 Domain-Driven Design (DDD)
A aplicação segue os princípios do DDD, organizando o código em camadas distintas que representam diferentes responsabilidade:

- **Camada de Domínio** Contém as entidades principais do negócio, encapsulando a lógica de negócios e regras específica.
- **Camada de Aplicação** Responsável por orquestrar as operações do sistema, coordenando a interação entre as entidades de domínio e os serviços externo.
- **Camada de Infraestrutura** Implementa detalhes técnicos, como acesso a bancos de dados e integração com serviços externo.
- **Camada de Apresentação (API)** Exponibiliza os endpoints RESTful para interação com os clientes, manipulando requisições e respostas HTT.
Essa separação promove um design modular, facilitando a manutenção e evolução do sistem.

### 📦 Data Transfer Objects (DTOs)

Os DTOs são utilizados para transferir dados entre as camadas da aplicação, especialmente entre a camada de apresentação e a de aplicaço Eles ajudam a desacoplar as entidades de domínio da representação externa dos dados, permitindo maior flexibilidade e segurana.

Por exemplo, ao criar um novo produto, um `ProdutoDTO` pode ser utilizado para receber os dados da requisição, que são então convertidos em uma entidade de domínio antes de serem processads.

### 🛡️ Tratamento de Exceções

A aplicação implementa um mecanismo centralizado para tratamento de exceções, garantindo que erros sejam capturados e respostas apropriadas sejam retornadas aos cliene. Isso incui:

- **Exceções Personalizadas*: Definição de exceções específicas para diferentes cenários de erro, como `RecursoNaoEncontradoException` ou `OperacaoNaoPermitidaExceptin`.
- **Manipuladores Globais*: Uso de anotações como `@ControllerAdvice` para interceptar exceções lançadas em qualquer parte da aplicação e mapear para respostas HTTP adequaas.

Esse tratamento consistente de erros melhora a robustez da aplicação e fornece feedback claro aos usuáros.

### ✅ Validação de Dados

A aplicação utiliza anotações de validação para assegurar que os dados recebidos nas requisições atendam aos critérios esperados antes de serem processao. Isso incui:

- **Anotações de Validação*: Uso de anotações como `@NotNull`, `@Size`, `@Email`, entre outras, nos campos dos DTOs para definir restrições de validaão.
- **Validação Automática*: Integração com o Spring Validator para que as validações sejam aplicadas automaticamente durante o binding das requisiçes.
- **Mensagens de Erro Personalizadas*: Definição de mensagens claras e específicas para cada tipo de erro de validação, facilitando a correção por parte dos usuáros.

Esse mecanismo de validação preventiva ajuda a manter a integridade dos dados e a evitar erros durante o processameto.

--






### ⚙️ Pré-requisitos
Antes de executar a aplicação, certifique-se de ter instalado em seu ambiente de desenvolviment:

- **Java 21+**
- **Maven** para gerenciamento de dependências e build
- **PostgreSQL 16+**
- **IDE** (por exemplo, IntelliJ IDEA, Eclipse, VS Code)

---

### 🚀 Instruções para Executar a Aplicação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/DavidSantosDeveloper/api_rest_java_spring_ecommerce_jogos.git
   cd api_rest_java_spring_ecommerce_jogos
   ```

2. **Configure o banco de dados PostgreSQL:**

   - Crie um banco de dados no PostgreSQL para a aplicação.
   - Atualize as configurações de conexão com o banco de dados no arquivo `application.properties`, localizado em `src/main/resources/`, com as informações do seu ambiente:

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     ```

3. **Compile e execute a aplicação:**

   Utilize o Maven para compilar e executar a aplicação:

   ```bash
   mvn spring-boot:run
   ```

   A aplicação estará disponível em: `http://localhost:8080/api`

---

### 📘 Endpoints Disponíveis

A API oferece os seguintes endpoints para gerenciamento das entidades:

#### 1. **Cargos**

- **GET /cargos**: Lista todos os cargos.
- **GET /cargos/{id}**: Retorna os detalhes de um cargo específico.
- **POST /cargos**: Cria um novo cargo.
- **PUT /cargos/{id}**: Atualiza as informações de um cargo existente.
- **DELETE /cargos/{id}**: Remove um cargo do sistema.

#### 2. **Carrinhos**

- **GET /carrinhos**: Lista todos os carrinhos.
- **GET /carrinhos/{id}**: Retorna os detalhes de um carrinho específico.
- **POST /carrinhos**: Cria um novo carrinho.
- **PUT /carrinhos/{id}**: Atualiza as informações de um carrinho existente.
- **DELETE /carrinhos/{id}**: Remove um carrinho do sistema.

#### 3. **Clientes**

- **GET /clientes**: Lista todos os clientes.
- **GET /clientes/{id}**: Retorna os detalhes de um cliente específico.
- **POST /clientes**: Cria um novo cliente.
- **PUT /clientes/{id}**: Atualiza as informações de um cliente existente.
- **DELETE /clientes/{id}**: Remove um cliente do sistema.

#### 4. **Funcionários**

- **GET /funcionarios**: Lista todos os funcionários.
- **GET /funcionarios/{id}**: Retorna os detalhes de um funcionário específico.
- **POST /funcionarios**: Cria um novo funcionário.
- **PUT /funcionarios/{id}**: Atualiza as informações de um funcionário existente.
- **DELETE /funcionarios/{id}**: Remove um funcionário do sistema.

#### 5. **Itens de Carrinho**

- **GET /itens-carrinho**: Lista todos os itens de carrinho.
- **GET /itens-carrinho/{id}**: Retorna os detalhes de um item de carrinho específico.
- **POST /itens-carrinho**: Adiciona um novo item ao carrinho.
- **PUT /itens-carrinho/{id}**: Atualiza as informações de um item de carrinho existente.
- **DELETE /itens-carrinho/{id}**: Remove um item do carrinho.

#### 6. **Itens de Venda**

- **GET /itens-venda**: Lista todos os itens de venda.
- **GET /itens-venda/{id}**: Retorna os detalhes de um item de venda específico.
- **POST /itens-venda**: Adiciona um novo item à venda.
- **PUT /itens-venda/{id}**: Atualiza as informações de um item de venda existente.
- **DELETE /itens-venda/{id}**: Remove um item da venda.

#### 7. **Produtos**

- **GET /produtos**: Lista todos os produtos.
- **GET /produtos/{id}**: Retorna os detalhes de um produto específico.
- **POST /produtos**: Cria um novo produto.
- **PUT /produtos/{id}**: Atualiza as informações de um produto existente.
- **DELETE /produtos/{id}**: Remove um produto do sistema.

#### 8. **Usuários**

- **GET /usuarios**: Lista todos os usuários.
- **GET /usuarios/{id}**: Retorna os detalhes de um usuário específico.
- **POST /usuarios**: Cria um novo usuário.
- **PUT /usuarios/{id}**: Atualiza as informações de um usuário existente.
- **DELETE /usuarios/{id}**: Remove um usuário do sistema.

#### 9. **Vendas**

- **GET /vendas**: Lista todas as vendas.
- **GET /vendas/{id}**: Retorna os detalhes de uma venda específica.
- **POST /vendas**: Registra uma nova venda.
- **PUT /vendas/{id}**: Atualiza as informações de uma venda existente.
- **DELETE /vendas/{id}**: Remove uma venda do sistema.


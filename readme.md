# Projeto NeoStore

Este projeto foi desenvolvido utilizando a **Arquitetura Hexagonal** (também conhecida como Ports and Adapters). Embora não fosse estritamente necessário adotar essa abordagem para resolver o desafio proposto, optei por utilizá-la como uma oportunidade de aprendizado e prática sobre essa arquitetura.

## Tecnologias Utilizadas

- **Backend:**
  - Java
  - Jakarta EE
  - Maven

- **Frontend:**
  - AngularJS
  - JavaScript
  - Bootstrap

- **Banco de Dados:**
  - H2

### Componentes da Arquitetura Hexagonal na Sua Estrutura


  #### **Adapters (`com.br.neostore.adapters`)**
  Representam os pontos de comunicação com o mundo externo. Contêm implementações concretas das portas definidas na aplicação.
  
  **Pacotes dentro de `adapters`:**
  - **`in` (Entrada)** → Define como a aplicação recebe dados:
    - `controller`: Expõe endpoints REST (ex: Spring Controller).
    - `exception`: Tratamento de exceções específicas para a camada de entrada.
  - **`out` (Saída)** → Define como a aplicação envia dados ou interage com serviços externos:
    - `repository`: Para persistência de dados (ex: JPA, JDBC).
    - `entity`: Modelos representando tabelas do banco de dados.
    - `mapper`: Conversões entre entidades de banco e objetos de domínio.
  
  ---
  
  #### **Application (`com.br.neostore.application`)**
  Contém as regras de negócio puras, sem dependências diretas de frameworks ou tecnologia específica.
  
  **Pacotes dentro de `application`:**
  - **`core`** → A lógica central da aplicação:
    - `domain`: Representa os objetos de domínio puro.
    - `usecase`: Implementação de casos de uso (onde ocorre a regra de negócio principal).
  - **`ports`** → Interfaces que conectam a aplicação com os adapters:
    - `in`: Define as interfaces dos casos de uso (ex: `DeleteFornecedorByIdUseCase`).
    - `out`: Define interfaces para repositórios ou serviços externos.
  
  ---

  #### **Outros Pacotes**
  - **`config`**: Configurações da aplicação (ex: beans, security, properties).

## Sobre o Desenvolvimento...

Durante o desenvolvimento, enfrentei um problema relacionado à injeção de dependências na configuração das **UseCases**. 
O problema estava relacionado ao uso incorreto da anotação `@Produces`. 
Após investigar por aproximadamente dois dias, percebi que estava utilizando o import errado para essa anotação, o que causava falhas na injeção. Após corrigir o import, o problema foi resolvido.


Nesse desse desafio, consegui implementar a arquitetura como eu queria, a partir do curso que realizei na udemy [Curso Udemy](https://www.udemy.com/course/arquitetura-hexagonal-ou-ports-and-adapters-na-pratica/) na qual, apesar de ser um pouco recente, consegui aprender um pouco sobre a importância da separação de responsabilidades e a flexibilidade que a Arquitetura Hexagonal oferece. 


Ela realmente é um pouco mais verbosa do que a gente consideraria simples, exemplo: Ter uma interface de service, realizar a implementação dela e em seguida utilizar no nosso controller.
Mas a flexibilidade que ela traz é muito boa, pois conseguimos trocar a implementação de forma mais simples e rápida, além de conseguir realizar testes unitários de forma mais simples.


Tambem tentei utilizar um pouco de handler de exception global, onde consegui implementar um handler para as exceções que podem ocorrer na aplicação, como por exemplo, o `SupplierNotFoundException`, onde ao tentar buscar um fornecedor que não existe, ele retorna uma mensagem de erro personalizada.
Nesse Handler eu tentei fazer com que para quase todos os problemas ele retornase um json com algumas informações, como o status, a mensagem e o timestamp do erro. Para utilizar o OffSetDateTime, tive que registrar um module de ObjectMapper para conseguir serializar o OffSetDateTime, já que o padrão do Jackson não consegue serializar ele por padrão.


Utilizei or MapStruct para realizar o mapeamento entre as entidades do banco e os DTOs, já que o MapStruct é uma biblioteca muito boa para realizar esse tipo de mapeamento, além de ser bem rápida, já que ele gera o código em tempo de compilação.

Estarei colocando o que foi desenvolvido a partir dos desafios que foram impostos no PDF:

### Back-End
Desafios
1. Validação de CNPJ ✅
2. E-mail válido ✅
3. Listagem de fornecedores paginada. (sugestão, 5 em 5) ✅
4. Fazer um serviço de importação de fornecedores a partir de JSON ✅

Diferenciais:
2. CDI ✅ (Penei um pouco para entender, mas até que o funcionamento não é tão diferente quanto o do Spring.)

### Front-End
Desafios
1. Validar os campos: Nome, CNPJ, e-mail como campos obrigatórios; ✅
2. Validar máscaras dos campos: CNPJ e e-mail. 🔄 (Parcialmente Implementado. Tentei utilizar alguma biblioteca, tipo angular-input-masks, mas não consegui fazer funcionar, então fiz uma validação simples com regex.)


### Como Executar o Projeto NeoStore

#### Pré-requisitos
1. **Java**: Certifique-se de ter o JDK 17 ou superior instalado.
2. **Maven**: Instale o Maven (versão 3.8.1 ou superior).
3. **Tomcat**: Baixe e configure o Apache Tomcat (versão 10.x ou superior).

---

### Há duas formas para executar o projeto:

### Atráves da IDE

---

#### Passo 1: Clonar o projeto pelo github
Clone o repositório do projeto para sua máquina local:
1. Vá em **File > New > Project from Version Control**.
2. Selecione **Git**.
3. Cole a URL do repositório:
   ```
   https://github.com/LuizMafraJNR/desafio-neostore.git
    ```
4. Escolha o diretório onde deseja clonar o projeto.
5. Clique em **Clone**.
6. Aguarde o download do projeto.
7. Após o download, clique em **Open** para abrir o projeto no IntelliJ.
8. Aguarde o IntelliJ carregar o projeto e resolver as dependências do Maven.
---

#### Passo 2: Configurar o Tomcat no IntelliJ
1. Vá em **File > Settings > Build, Execution, Deployment > Application Servers**.
2. Clique no botão **+** e selecione **Tomcat Server**.
3. Adicione o diretório de instalação do Tomcat.

---

#### Passo 3: Configurar a Configuração de Execução
1. Clique em **Run > Edit Configurations**.
2. Clique no botão **+** e selecione **Tomcat Server > Local**.
3. Configure os seguintes itens:
  - **Name**: Dê um nome para a configuração, como `NeoStore`.
  - **Application Server**: Selecione o Tomcat configurado anteriormente.
  - **Deployment**: Clique em **+ > Artifact** e selecione o arquivo `.war` gerado pelo Maven (`neostore:war`).
4. Clique em **OK** para salvar.

---

#### Passo 4: Compilar e Executar
1. No IntelliJ, abra o painel **Maven** (geralmente no lado direito).
2. Clique em lifecycle e depois selecione **clean** e **install** para compilar o projeto.
3. Após a compilação, clique em **Run > Run 'NeoStore'** para iniciar o Tomcat.

---

#### Passo 5: Acessar a Aplicação
1. Abra o navegador e acesse:
   ```
   http://localhost:8080/neostore
   ```
2. A aplicação estará disponível para uso.

### Sem ser pela IDE

#### 1: Clonar o Repositório
Clone o repositório do projeto para sua máquina local:
```bash
git clone https://github.com/LuizMafraJNR/desafio-neostore.git
cd desafio-neostore
```

---

#### 2: Compilar e Construir o Projeto
Execute o comando abaixo para limpar, compilar e empacotar o projeto:
```bash
mvn clean install
```
- O comando `clean` remove arquivos antigos de build.
- O comando `install` compila o código, executa os testes e gera o arquivo `.war` na pasta `target`.

---

#### 3: Configurar o Tomcat
1. **Baixe o Tomcat**:
  - Acesse [Apache Tomcat](https://tomcat.apache.org/) e baixe a versão 10.x ou superior.

2. **Extraia o Tomcat**:
  - Extraia o arquivo baixado para um diretório de sua escolha.

3. **Copie o Arquivo `.war`**:
  - Após o build, copie o arquivo gerado (`target/neostore.war`) para a pasta `webapps` do Tomcat.

---

#### 4: Iniciar o Tomcat
1. Navegue até o diretório `bin` do Tomcat.
2. Execute o script de inicialização:
  - No Windows:
    ```bash
    startup.bat
    ```
  - No Linux/Mac:
    ```bash
    ./startup.sh
    ```
---

#### 5: Acessar a Aplicação
1. Abra o navegador e acesse:
   ```
   http://localhost:8080/neostore
   ```
2. A aplicação estará disponível para uso.

---

## Endpoints

### Fornecedor
- **Listar Fornecedores**: 
  - GET /fornecedores`
    - Parâmetros: `Nenhum`
    - Exemplo: `GET http://localhost:8080/neostore/api/v1/fornecedores`

  <br>
- **Listar Fornecedor por ID**: 
  - GET /fornecedores/{id}
    - Parâmetros: `Id`
    - Exemplo: `GET http://localhost:8080/neostore/api/v1/fornecedores/1`

    <br>
- **Listar Fornecedor Paginado**:
- GET /fornecedores/paginado
    - Parâmetros: `page`, `size`
    - Exemplo: `GET http://localhost:8080/neostore/api/v1/fornecedores/paginado?page=1&size=10`

    <br>
- **Criar Fornecedor**: 
  - POST /fornecedores
    - Parâmetros: `Name`, `E-mail`, `Description`,`CNPJ`, 
    - Exemplo: `POST http://localhost:8080/neostore/api/v1/fornecedores`
    ```json
      {
        "name": "Fornecedor A",
        "email": "fornecedorA@email.com",
        "description": "Fornecedor de equipamentos eletrônicos",
        "cnpj": "12.345.678/0001-22"
      }
      
- **Importar Fornecedor**:
  - POST /fornecedores/importar
    - Paramêtros: `Name`, `E-mail`, `Description`,`CNPJ`
    - Exemplo: `GET http://localhost:8080/neostore/api/v1/fornecedores/importar`
    ```json
    [
      {
        "name": "Fornecedor A",
        "email": "fornecedorA@email.com",
        "description": "Fornecedor de equipamentos eletrônicos",
        "cnpj": "12.345.678/0001-01"
      },
      {
        "name": "Distribuidora Tech",
        "email": "contato@distribuidoratech.com.br",
        "description": "Distribuição de componentes de informática",
        "cnpj": "23.456.789/0001-02"
      },
      {
        "name": "Suprimentos Express",
        "email": "vendas@suprimentosexpress.com",
        "description": "Materiais de escritório e papelaria",
        "cnpj": "34.567.890/0001-03"
      },
      {
        "name": "IndustrialMaq",
        "email": "comercial@industrialmaq.ind.br",
        "description": "Máquinas industriais e equipamentos pesados",
        "cnpj": "45.678.901/0001-04"
      },
      {
        "name": "Alimentos do Sul",
        "email": "pedidos@alimentosdosul.com.br",
        "description": "Distribuidor de alimentos industrializados",
        "cnpj": "56.789.012/0001-05"
      },
      {
        "name": "Química Brasil",
        "email": "quimicabrasil@qbr.com",
        "description": "Produtos químicos e soluções industriais",
        "cnpj": "67.890.123/0001-06"
      },
      {
        "name": "TechSolutions",
        "email": "comercial@techsolutions.com.br",
        "description": "Serviços de TI e infraestrutura",
        "cnpj": "78.901.234/0001-07"
      },
      {
        "name": "TransportaNorte",
        "email": "operacoes@transportanorte.com",
        "description": "Serviços de logística e transporte",
        "cnpj": "89.012.345/0001-08"
      },
      {
        "name": "MóveisEMP",
        "email": "atendimento@moveisemp.com.br",
        "description": "Mobiliário corporativo e escritórios",
        "cnpj": "90.123.456/0001-09"
      },
      {
        "name": "Construtora Horizonte",
        "email": "projetos@construtorahorizonte.com",
        "description": "Material de construção e ferramentas",
        "cnpj": "01.234.567/0001-10"
      }
    ]

  <br>
- **Atualizar Fornecedor**: 
  - PUT /fornecedores/{id}
    - Parâmetros: `Id`, `Name`, `E-mail`, `Description`,`CNPJ`
    - Exemplo: `PUT http://localhost:8080/neostore/api/v1/fornecedores/1`
    ```json
      {
        "name": "Fornecedor A",
        "email": "fornecedorA@email.com",
        "description": "Fornecedor de equipamentos eletrônicos",
        "cnpj": "12.345.678/0001-19"
      }

    <br>
- **Deletar Fornecedor**: 
  - DELETE /fornecedores/{id}
    - Parâmetros: `Id`
    - Exemplo: `DELETE http://localhost:8080/neostore/api/v1/fornecedores/1`
        

## Considerações Finais

Este projeto tive algumas complicações bem por falta de atenção, principalmente no CDI. Tive que fazer algumas pesquisas para entender novamente como que fazia para realizar a persistência de dados, já que não utilizo o EntityManager diariamente(no baixo nivel), então acabei esquecendo algumas coisas, por estar acostumado a utilizar o Spring.

Mas no geral, consegui implementar o que foi pedido e ainda consegui aprender um pouco mais sobre a Arquitetura Hexagonal, que é uma arquitetura muito boa para se trabalhar, principalmente quando se tem um projeto grande, claro que aqui nesse eu não precisava utilizar, eu matei uma formiga com uma bazooka mas consegui aprender um pouco mais sobre a arquitetura e como ela funciona.
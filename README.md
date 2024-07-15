Visão Geral do Projeto

Este projeto é uma aplicação Spring Boot para gerenciamento de usuários, pedidos, categorias e produtos com uma API RESTful. A aplicação utiliza Spring Data JPA para interações com o banco de dados e um banco de dados H2 em memória para fins de teste.

Configuração do Projeto

Configuração do Spring Initializr

Ferramenta de Build: Maven
Linguagem: Java 17
Empacotamento: JAR
Dependências: Spring Web
Entidade e Recurso de Usuário

Checklist Básico da Entidade:
Atributos básicos
Associações (instanciar coleções)
Construtores
Getters & Setters (coleções: apenas get)
hashCode & equals
Implementa Serializable

Banco de Dados H2, Perfil de Teste, JPA

Dependências:
xml
Copiar código
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

Arquivos de Configuração:

application.properties:
properties
Copiar código
spring.profiles.active=test
spring.jpa.open-in-view=true
application-test.properties:
properties

# DATASOURCE 
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password= 

# H2 CLIENT 
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA, SQL 
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
Repositório JPA, Injeção de Dependências, População do Banco de Dados
Checklist:
UserRepository estende JPARepository<User, Long>
Classe de configuração para o perfil "test"
@Autowired UserRepository
Instanciar e persistir objetos na memória

Objetos:

User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

Camada de Serviço, Registro de Componentes
Entidade Pedido e ISO 8601
Entidade com associação "to many", lazy loading, JsonIgnore
Repositório
População
Serviço
Recurso
Objetos:

Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

Enum OrderStatus
Entidade Categoria
Objetos:
java
Copiar código
Category cat1 = new Category(null, "Eletrônicos");
Category cat2 = new Category(null, "Livros");
Category cat3 = new Category(null, "Computadores");
Entidade Produto
Objetos:

Product p1 = new Product(null, "O Senhor dos Anéis", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

Associação Many-to-Many com JoinTable
Entidade OrderItem com Associação Many-to-Many e Atributos Extras
Checklist:
OrderItemPK
OrderItem
Associação one-to-many com Pedido
População
Objetos:

OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

Associação One-to-Many entre Produto e OrderItem
Associação One-to-One com Pagamento
Métodos Subtotal e Total
Inserção de Usuário
Checklist:
UserService
UserResource
Teste:

{
 "name": "Bob Brown",
 "email": "bob@gmail.com",
 "phone": "977557755",
 "password": "123456"
}

Exclusão de Usuário

Checklist:
UserService
UserResource
Atualização de Usuário
Checklist:
UserService
UserResource
Teste:

{
 "name": "Bob Brown",
 "email": "bob@gmail.com",
 "phone": "977557755"
}

Tratamento de Exceções - findById
Checklist:
NOVA CLASSE: services.exceptions.ResourceNotFoundException
NOVA CLASSE: resources.exceptions.StandardError
NOVA CLASSE: resources.exceptions.ResourceExceptionHandler

UserService
Tratamento de Exceções - delete

Checklist:

NOVA CLASSE: services.exceptions.DatabaseException
ResourceExceptionHandler

UserService
EmptyResultDataAccessException
DataIntegrityViolationException
Tratamento de Exceções - update

Checklist:
UserService
EntityNotFoundException

Este README fornece um guia abrangente para configurar e entender a estrutura e os componentes do projeto. Siga a checklist e os snippets de código para implementar os respectivos recursos e configurações.

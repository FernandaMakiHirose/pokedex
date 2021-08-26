# Construindo um Pokédex com Spring Webflux
Construí um pokedéx (repositório de pokemon) através de uma Rest API feita com Spring Webflux, o módulo reativo do Spring Boot. Ao final do projeto vi os conceitos de programação reativa, handler, mono e flux e também criado uma Rest Api com seus dados salvos no MongoDB.

## Webflux 
É um módulo do Spring Boot para criar aplicações reativas, ou seja, é possível fazer uma chamada de eventos com diversas requisições sem que elas sejam bloqueadas, isso tudo para fazer requisições assíncronas de serviço (fazer mais de uma tarefa ao mesmo tempo).

## Spring Initializr
- Maven
- Spring Boot 2.2.6 
- Java 8
- Jar 
- Dependências: Spring Reactive Web (é o Spring Web para trabalhar de forma reativa), Spring Data Reactive MongoDB, Embedded MongoDB Database

## Observação
Após gerar o projeto em zip foi necessário adicionar a dependência `de.flapdoodle.embed` logo abaixo do `spring-boot-starter-webflux` e excluir o `<scope>test</scope>` do mesmo dentro do arquivo `pom.xml` para funcionar corretamente.

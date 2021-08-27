# Construindo um Pokédex com Spring Webflux
Construí um pokedéx (repositório de pokemon) através de uma Rest API feita com Spring Webflux, o módulo reativo do Spring Boot. Ao final do projeto vi os conceitos de programação reativa, handler, mono e flux e também criado uma Rest Api com seus dados salvos no MongoDB.

## Comandos
Para executar o projeto:
>mvn spring-boot:run

Pokemons:
>http://localhost:8080/pokemons

Eventos:
>http://localhost:8080/pokemons/events

Id (Substitua o `{id}` pelo id do pokemon):
>http://localhost:8080/pokemons/{id}

## Webflux 
É um módulo do Spring Boot para criar aplicações reativas, ou seja, é possível fazer uma chamada de eventos com diversas requisições sem que elas sejam bloqueadas, isso tudo para fazer requisições assíncronas de serviço (fazer mais de uma tarefa ao mesmo tempo).

## Spring Initializr
- Maven
- Spring Boot 2.2.6 
- Java 8
- Jar 
- Dependências: Spring Reactive Web (é o Spring Web para trabalhar de forma reativa), Spring Data Reactive MongoDB, Embedded MongoDB Database, Lombok

## Observação
- Após gerar o projeto em zip foi necessário adicionar a dependência `de.flapdoodle.embed` logo abaixo do `spring-boot-starter-webflux` e excluir o `<scope>test</scope>` do mesmo dentro do arquivo `pom.xml` para funcionar corretamente.
- O servidor do WebFlux é o Netty, sem usar o WebFlux é o Tomcat, mas é possível usar o Tomcat no WebFlux
- Para o Lombok funcionar precisei adicionar `<version>1.18.20</version>` conforme [esse](https://stackoverflow.com/questions/66801256/java-lang-illegalaccesserror-class-lombok-javac-apt-lombokprocessor-cannot-acce) artigo

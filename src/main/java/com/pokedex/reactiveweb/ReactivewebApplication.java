package com.pokedex.reactiveweb;

import com.pokedex.reactiveweb.model.Pokemon;
import com.pokedex.reactiveweb.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactivewebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivewebApplication.class, args); }

		@Bean
		/*ReactiveMongoOperations operations não foi usado, mas pode ser usado durante a aplicação se quiser usar o código e colocar no Atlas e vai trazer o código do repository. O método retorna um argumento, um flux (fluxo de dados), com os dados dos pokemons que são salvos*/
		CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository) {
			return args -> {
				Flux<Pokemon> pokemonFlux = Flux.just(
						new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
						new Pokemon(null, "Charizard", "Fogo", "Blaze", 90.05),
						new Pokemon(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
						new Pokemon(null, "Blastoise", "Marisco", "Torrente", 6.09))
						.flatMap(repository::save);

				// acha todos os dados e retorna uma mensagem
				pokemonFlux
						.thenMany(repository.findAll())
						.subscribe(System.out::println);
			};
		}

}

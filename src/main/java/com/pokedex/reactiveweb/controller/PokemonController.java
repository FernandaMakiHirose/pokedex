package com.pokedex.reactiveweb.controller;


import com.pokedex.reactiveweb.model.Pokemon;
import com.pokedex.reactiveweb.model.PokemonEvent;
import com.pokedex.reactiveweb.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    // cria a variável do repository e o método que toda vez que for chamado vai chamar o repository
    private PokemonRepository repository;
    public PokemonController(PokemonRepository repository) { this.repository = repository; }

    // mostra todos os pokemons
    @GetMapping
    public Flux<Pokemon> getAllPokemons() {return repository.findAll();}

    // busca os pokemons pelo id, pelo mono e não pelo flux, porque apenas um requisição é mono e mais de uma é flux, se achar o pokemon vai retornar um status ok se der errado vai dizer que não encontrou
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id) {
        return repository.findById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // cria um pokemon
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        return repository.save(pokemon);
    }

    // atualiza o pokemon, encontra ele pelo id, faz o set e o get e por fim se achar o pokemon vai retornar um status ok se der errado vai dizer que não encontrou
    @PutMapping("{id}")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id") String id,
                                                       @RequestBody Pokemon pokemon) {
        return repository.findById(id)
                .flatMap(existingPokemon -> {
                    existingPokemon.setNome(pokemon.getNome());
                    existingPokemon.setCategoria(pokemon.getCategoria());
                    existingPokemon.setHabilidades(pokemon.getHabilidades());
                    existingPokemon.setPeso(pokemon.getPeso());
                    return repository.save(existingPokemon);
                })
                .map(updatePokemon -> ResponseEntity.ok(updatePokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value = "id") String id) {
        return repository.findById(id)
                .flatMap(existingPokemon ->
                        repository.delete(existingPokemon)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // deleta todos os pokemons
    @DeleteMapping
    public Mono<Void> deleteAllPokemons() {
        return repository.deleteAll();
    }

    // quer produzir um evento de stream, retorna um fluxo dos eventos do pokemon, a requisição vai aparecer de 5 em 5 segundos
    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new PokemonEvent(val, "Product Event")
                );
    }
}

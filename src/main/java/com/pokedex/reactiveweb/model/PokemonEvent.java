package com.pokedex.reactiveweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Data
@AllArgsConstructor
public class PokemonEvent {
    private Long eventId;
    private String eventType;
}

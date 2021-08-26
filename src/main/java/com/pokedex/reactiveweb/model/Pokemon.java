package com.pokedex.reactiveweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@AllArgsConstructor
@Document
public class Pokemon {
    @Id
    private String id;
    private String nome;
    private String categoria;
    private String habilidades;
    private Double peso;
}

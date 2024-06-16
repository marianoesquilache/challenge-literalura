package com.mariano.literalura.Literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDto(

            Long id,
            @JsonAlias("name")
            String name,
            @JsonAlias("birth_year")
            int anoNac,
            @JsonAlias("death_year")
            int anoMue) {

    @Override
    public String toString() {
        return "AutorDto{" +
                "name='" + name + '\'' +
                ", anoNac=" + anoNac +
                ", anoMue=" + anoMue +
                '}';
    }
}



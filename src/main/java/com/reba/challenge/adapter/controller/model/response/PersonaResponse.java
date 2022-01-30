package com.reba.challenge.adapter.controller.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.reba.challenge.domain.Persona;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.List;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class PersonaResponse {
    private String nroDocumento;
    private String nombre;
    private Integer edad;
    @With
    private String status;
    List<ContactoResponse> list;

    public static PersonaResponse fromDomain(Persona persona) {
        return PersonaResponse.builder()
                .nroDocumento(persona.getNroDocumento())
                .nombre(persona.getNombre())
                .edad(persona.getEdad())
                .list(persona.getContactos().stream().map(ContactoResponse::fromDomain).collect(Collectors.toList()))
                .build();
    }
}

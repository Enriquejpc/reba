package com.reba.challenge.adapter.controller.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.reba.challenge.domain.Contacto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactoResponse {
    private String nombre;
    private String telefono;

    public static ContactoResponse fromDomain(Contacto contacto){
        return ContactoResponse.builder()
                .nombre(contacto.getNombre())
                .telefono(contacto.getTelefono())
                .build();
    }
}

package com.reba.challenge.adapter.controller.model.request;

import com.reba.challenge.domain.Contacto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactoRequest {
    private String nombre;
    private String telefono;

    public static Contacto toCommand(ContactoRequest request){
        return Contacto.builder()
                .nombre(request.getNombre())
                .telefono(request.getTelefono())
                .build();
    }
}

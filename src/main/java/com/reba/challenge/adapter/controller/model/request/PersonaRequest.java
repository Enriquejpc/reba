package com.reba.challenge.adapter.controller.model.request;

import com.reba.challenge.domain.Persona;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class PersonaRequest {

    @NotEmpty(message = "Debe indicar el documento")
    @NotNull(message = "Debe indicar el documento")
    private String nroDocumento;

    private String nombre;

    @Min(value = 18, message = "Debe ser mayor a 18 años")
    private Integer edad;

    @Size(min = 1, message = "Debe indicar al menos un contacto")
    private List<ContactoRequest> contactos;

    public Persona toPersonaCommand() {
        return Persona.builder()
                .nroDocumento(nroDocumento)
                .nombre(nombre)
                .edad(edad)
                .contactos(contactos.stream().map(ContactoRequest::toCommand).collect(Collectors.toList()))
                .build();
    }
}

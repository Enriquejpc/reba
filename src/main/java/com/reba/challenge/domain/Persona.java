package com.reba.challenge.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Persona {
    private String nroDocumento;
    private String nombre;
    private Integer edad;
    private List<Contacto> contactos;
}

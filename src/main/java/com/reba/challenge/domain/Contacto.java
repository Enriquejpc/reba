package com.reba.challenge.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Contacto {
    String nombre;
    String telefono;
}

package com.reba.challenge.application.port.in;

import com.reba.challenge.domain.Persona;

import java.util.Optional;

public interface ObtenerPersonaQuery {
    Optional<Persona> obtenerByNroDocumento(String nroDocumento);
}

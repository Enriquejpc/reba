package com.reba.challenge.application.port.out;

import com.reba.challenge.domain.Persona;

import java.util.Optional;

public interface PersonaRepository {
    Persona saveOrUpdate(Persona persona);

    Optional<Persona> getByNroDocumento(String nroDocumento);

    void delete(String nroDocumento);

}

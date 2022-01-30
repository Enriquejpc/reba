package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.ObtenerPersonaQuery;
import com.reba.challenge.application.port.out.PersonaRepository;
import com.reba.challenge.domain.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ObtenerPersonaUseCase implements ObtenerPersonaQuery {

    private final PersonaRepository personaRepository;

    public ObtenerPersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Optional<Persona> obtenerByNroDocumento(String nroDocumento) {
        return personaRepository.getByNroDocumento(nroDocumento);
    }
}

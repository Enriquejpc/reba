package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.PersonaQuery;
import com.reba.challenge.application.port.out.PersonaRepository;
import com.reba.challenge.domain.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetPersonaUseCase implements PersonaQuery {

    private final PersonaRepository personaRepository;

    public GetPersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Optional<Persona> obtenerByNroDocumento(String nroDocumento) {
        return personaRepository.getByNroDocumento(nroDocumento);
    }
}

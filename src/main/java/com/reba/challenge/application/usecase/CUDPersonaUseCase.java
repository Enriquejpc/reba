package com.reba.challenge.application.usecase;

import com.reba.challenge.adapter.jdbc.exception.BusinessException;
import com.reba.challenge.application.exception.NotFoundException;
import com.reba.challenge.application.port.in.PersonaCommand;
import com.reba.challenge.application.port.out.PersonaRepository;
import com.reba.challenge.config.ErrorCode;
import com.reba.challenge.domain.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CUDPersonaUseCase implements PersonaCommand {
    private final PersonaRepository personaRepository;
    private final GetPersonaUseCase obtenerGetPersonaUseCase;

    public CUDPersonaUseCase(PersonaRepository personaRepository, GetPersonaUseCase obtenerGetPersonaUseCase) {
        this.personaRepository = personaRepository;
        this.obtenerGetPersonaUseCase = obtenerGetPersonaUseCase;
    }

    @Override
    public Persona crear(Persona command) {
        Optional<Persona> persona = obtenerGetPersonaUseCase.obtenerByNroDocumento(command.getNroDocumento());
        if (persona.isPresent())
            throw new BusinessException(ErrorCode.RESOURCE_DUPLICATE_ERROR);
        return personaRepository.saveOrUpdate(command);
    }

    @Override
    public Persona actualizar(Persona command) {
        var persona = (obtenerGetPersonaUseCase.obtenerByNroDocumento(command.getNroDocumento()))
                .orElseThrow(() -> new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND_ERROR));
        var updatedPersona = Persona.builder()
                .nroDocumento(persona.getNroDocumento())
                .nombre(command.getNombre())
                .edad(command.getEdad())
                .contactos(command.getContactos())
                .build();
        return personaRepository.saveOrUpdate(updatedPersona);
    }

    @Override
    public void borrar(String nroDocumento) {
        var persona = (obtenerGetPersonaUseCase.obtenerByNroDocumento(nroDocumento))
                .orElseThrow(() -> new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND_ERROR));
        personaRepository.delete(persona.getNroDocumento());
    }

}

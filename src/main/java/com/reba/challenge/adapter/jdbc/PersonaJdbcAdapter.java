package com.reba.challenge.adapter.jdbc;

import com.reba.challenge.adapter.jdbc.exception.BusinessException;
import com.reba.challenge.adapter.jdbc.model.PersonaEntityModel;
import com.reba.challenge.application.port.out.PersonaRepository;
import com.reba.challenge.config.ErrorCode;
import com.reba.challenge.domain.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class PersonaJdbcAdapter implements PersonaRepository {
    private final PersonaJpaRepository personaJpaRepository;

    public PersonaJdbcAdapter(PersonaJpaRepository personaJpaRepository) {
        this.personaJpaRepository = personaJpaRepository;
    }

    @Override
    public Persona saveOrUpdate(Persona persona) {
        log.info("Se guarda persona :: {}", persona);
        try {
            personaJpaRepository.save(PersonaEntityModel.fromDomain(persona));
            return persona;
        } catch (Exception exception) {
            log.error("Ocurrio error", exception);
            throw new BusinessException(ErrorCode.DATABASE_CONNECTION_ERROR);
        }
    }

    @Override
    public Optional<Persona> getByNroDocumento(String nroDocumento) {
        log.info("Buscando persona con documento :: {}", nroDocumento);
        Optional<PersonaEntityModel> persona = personaJpaRepository.findById(nroDocumento);
        return persona.map(PersonaEntityModel::toDomain);
    }

    @Override
    public void delete(String nroDocumento) {
        log.info("Borrando a la persona con documento :: {}", nroDocumento);
        personaJpaRepository.deleteById(nroDocumento);
    }
}

package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.out.PersonaRepository;
import com.reba.challenge.domain.Persona;
import mock.MockFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Obtener Persona Use Case Test")
@ExtendWith(MockitoExtension.class)
class GetPersonaUseCaseTest {

    @Mock
    PersonaRepository personaRepository;

    @Test
    @DisplayName("Cuando quiero obtener una persona y todo fue correcto entonces espero los datos de la persona")
    void crearPersona_Ok() {
        GetPersonaUseCase getPersonaUseCase = new GetPersonaUseCase(personaRepository);

        when(personaRepository.getByNroDocumento(anyString())).thenReturn(Optional.of(MockFactory.personaDomainMock()));

        Optional<Persona> persona = getPersonaUseCase.obtenerByNroDocumento("v1234");
        assertThat(persona).isNotNull();
        verify(personaRepository, times(1)).getByNroDocumento(anyString());
    }
}
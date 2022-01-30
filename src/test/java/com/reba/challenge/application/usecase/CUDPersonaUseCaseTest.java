package com.reba.challenge.application.usecase;

import com.reba.challenge.adapter.jdbc.exception.BusinessException;
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
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Crear, Editar, Borrar Person Use Case Test")
@ExtendWith(MockitoExtension.class)
class CUDPersonaUseCaseTest {

    @Mock
    PersonaRepository personaRepository;

    @Mock
    GetPersonaUseCase getPersonaUseCase;

    @Test
    @DisplayName("Cuando quiero insertar una persona , y todo fue correcto(no existe en la bd), espero la entidad persona, y al menos una ejecucion")
    void crearPersona_Ok() {
        CUDPersonaUseCase cudPersonaUseCase = new CUDPersonaUseCase(personaRepository, getPersonaUseCase);
        when(getPersonaUseCase.obtenerByNroDocumento(anyString())).thenReturn(Optional.empty());
        when(personaRepository.saveOrUpdate(MockFactory.personaDomainMock())).thenReturn(MockFactory.personaDomainMock());
        Persona persona = cudPersonaUseCase.crear(MockFactory.personaDomainMock());

        assertThat(persona).isNotNull();
        verify(getPersonaUseCase, times(1)).obtenerByNroDocumento(anyString());
    }

    @Test
    @DisplayName("Cuando quiero insertar una persona , y la persona ya existe en la bdd, espero una excepcion")
    void crearPersona_ExceptionPersonaExistente() {
        CUDPersonaUseCase cudPersonaUseCase = new CUDPersonaUseCase(personaRepository, getPersonaUseCase);
        when(getPersonaUseCase.obtenerByNroDocumento(anyString())).thenReturn(Optional.of(MockFactory.personaDomainMock()));

        Throwable thrown = catchThrowable(() -> cudPersonaUseCase.crear(MockFactory.personaDomainMock()));
        assertThat(thrown).isInstanceOf(BusinessException.class).hasMessage("Duplicated resource");
    }

}
package com.reba.challenge.adapter.jdbc;

import com.reba.challenge.adapter.jdbc.exception.BusinessException;
import com.reba.challenge.adapter.jdbc.model.PersonaEntityModel;
import com.reba.challenge.config.ErrorCode;
import mock.MockFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Component
@ExtendWith(MockitoExtension.class)
@DisplayName(" PersonaJdbcAdapter Repository test")
class PersonaJdbcAdapterTest {

    private final PersonaJpaRepository personaJpaRepository = Mockito.mock(PersonaJpaRepository.class);

    private final PersonaJdbcAdapter adapter = new PersonaJdbcAdapter(personaJpaRepository);

    @Test
    @DisplayName("Cuando quiero guardar los datos de la persona y todo fue correcto entonces espero los datos de la persona")
    void savePersona_Ok() {
        when(personaJpaRepository.save(any(PersonaEntityModel.class))).thenReturn(PersonaEntityModel.fromDomain(MockFactory.personaDomainMock()));
        Throwable throwable = catchThrowable(() -> adapter.saveOrUpdate(MockFactory.personaDomainMock()));
        assertThat(throwable)
                .doesNotThrowAnyException();
        verify(personaJpaRepository, times(1)).save(any(PersonaEntityModel.class));
    }

    @Test
    @DisplayName("Cuando quiero guardar los datos de la persona y ocurre una excepcion entonces espero la instancia de la excepcion ocurrida")
    void savePersona_Exception() {
        doThrow(new RuntimeException()).when(personaJpaRepository).save(any(PersonaEntityModel.class));
        Throwable throwable = catchThrowable(() -> adapter.saveOrUpdate(MockFactory.personaDomainMock()));
        assertThat(throwable)
                .isInstanceOf(BusinessException.class).hasMessage(ErrorCode.DATABASE_CONNECTION_ERROR.getReasonPhrase());
    }

}
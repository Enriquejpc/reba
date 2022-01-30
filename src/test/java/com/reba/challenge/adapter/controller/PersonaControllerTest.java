package com.reba.challenge.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reba.challenge.adapter.controller.model.response.PersonaResponse;
import com.reba.challenge.application.port.in.PersonaCommand;
import com.reba.challenge.application.port.in.PersonaQuery;
import com.reba.challenge.domain.Persona;
import mock.MockFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ontroller Adapter Test")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PersonaController.class)
class PersonaControllerTest {
    private final String CHALLENGE_URI = "/api/v1/challenge/persona";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PersonaCommand command;
    @MockBean
    private PersonaQuery query;

    @Test
    @DisplayName("Cuando quiero dar de alta una persona, no hay conflictos, entonces espero HttpStatus.Created")
    void postCrearPersona_Ok() throws Exception {
        PersonaResponse response = MockFactory.crearPersonaResponseMock();
        when(command.crear(any(Persona.class))).thenReturn(MockFactory.personaDomainMock());
        this.mockMvc.perform(
                        post(CHALLENGE_URI)
                                .content(objectMapper.writeValueAsString(MockFactory.personaRequestMock()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    @DisplayName("Cuando quiero dar de alta una persona, pero la edad es menor a 18, espero Internal Error Exception")
    void postCrearPersona_InternalErrorCausedByEdad() throws Exception {
        this.mockMvc.perform(
                        post(CHALLENGE_URI)
                                .content(objectMapper.writeValueAsString(MockFactory.personaEdadBadRequestMock()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Cuando quiero obtener los datos de una persona dado id, entonces la persona es encontrada, espero un Ok")
    void getPersona_Ok() throws Exception {
        String result = objectMapper.writeValueAsString(PersonaResponse.fromDomain(MockFactory.personaDomainMock()));
        when(query.obtenerByNroDocumento(anyString())).thenReturn(Optional.of(MockFactory.personaDomainMock()));
        this.mockMvc.perform(
                        get(CHALLENGE_URI.concat("/v1234")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

    @Test
    @DisplayName("Cuando quiero obtener los datos de una persona dado id, pero no existe la persona, espero NotFound")
    void getPersona_NotFound() throws Exception {
        when(query.obtenerByNroDocumento(anyString())).thenReturn(Optional.empty());
        this.mockMvc.perform(
                        get(CHALLENGE_URI.concat("/v1234")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
package com.reba.challenge.adapter.controller;

import com.reba.challenge.adapter.controller.model.request.PersonaRequest;
import com.reba.challenge.adapter.controller.model.response.PersonaResponse;
import com.reba.challenge.application.exception.NotFoundException;
import com.reba.challenge.application.port.in.ObtenerPersonaQuery;
import com.reba.challenge.application.port.in.PersonaCommand;
import com.reba.challenge.config.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/challenge/persona")
public class PersonaController {

    private final PersonaCommand personaCommand;
    private final ObtenerPersonaQuery obtenerPersonaQuery;

    public PersonaController(PersonaCommand personaCommand, ObtenerPersonaQuery obtenerPersonaQuery) {
        this.personaCommand = personaCommand;
        this.obtenerPersonaQuery = obtenerPersonaQuery;
    }

    @PostMapping
    public ResponseEntity<PersonaResponse> create(@RequestBody @Validated PersonaRequest body) {
        log.info("Persona:: body received {}", body);
        var persona = personaCommand.crear(body.toPersonaCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonaResponse.builder()
                .nroDocumento(persona.getNroDocumento())
                .status("Creado")
                .build());
    }

    @PutMapping
    public ResponseEntity<PersonaResponse> update(@RequestBody @Validated PersonaRequest body) {
        log.info("Update Persona:: body received {}", body);
        var persona = personaCommand.actualizar(body.toPersonaCommand());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PersonaResponse.builder()
                .nroDocumento(persona.getNroDocumento())
                .status("Actualizado")
                .build());
    }

    @GetMapping("/{nroDocumento}")
    public ResponseEntity<PersonaResponse> get(@PathVariable(name = "nroDocumento") String nroDocumento) {
        log.info("Looking Persona with id:: {}", nroDocumento);
        var persona = obtenerPersonaQuery.obtenerByNroDocumento(nroDocumento)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND_ERROR));
        PersonaResponse personaResponse = PersonaResponse.fromDomain(persona);
        return ResponseEntity.status(HttpStatus.OK).body(personaResponse.withStatus(HttpStatus.OK.getReasonPhrase()));
    }

    @DeleteMapping("/{nroDocumento}")
    public ResponseEntity<PersonaResponse> delete(@PathVariable(name = "nroDocumento") String nroDocumento) {
        log.info("Deleting Persona with id:: {}", nroDocumento);
        personaCommand.borrar(nroDocumento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(PersonaResponse.builder()
                .nroDocumento(nroDocumento)
                .status("Borrado")
                .build());
    }
}

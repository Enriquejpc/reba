package com.reba.challenge.application.port.in;

import com.reba.challenge.domain.Persona;

public interface PersonaCommand {
    Persona crear(Persona command);

    Persona actualizar(Persona command);

    void borrar(String nroDocumento);

}


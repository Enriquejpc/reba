package mock;

import com.reba.challenge.adapter.controller.model.request.ContactoRequest;
import com.reba.challenge.adapter.controller.model.request.PersonaRequest;
import com.reba.challenge.adapter.controller.model.response.PersonaResponse;
import com.reba.challenge.domain.Persona;

import java.util.List;

public class MockFactory {

    public static ContactoRequest contactoRequestMock() {
        return ContactoRequest.builder()
                .nombre("contacto")
                .telefono("telefono")
                .build();
    }

    public static PersonaRequest personaRequestMock() {
        return PersonaRequest.builder()
                .nroDocumento("v1234")
                .nombre("nombre")
                .edad(19)
                .contactos(List.of(contactoRequestMock()))
                .build();
    }


    public static PersonaRequest personaEdadBadRequestMock() {
        return PersonaRequest.builder()
                .nroDocumento("v1234")
                .nombre("nombre")
                .edad(17)
                .contactos(List.of(contactoRequestMock()))
                .build();
    }

    public static Persona personaDomainMock() {
        return personaRequestMock().toPersonaCommand();
    }

    public static PersonaResponse crearPersonaResponseMock() {
        return PersonaResponse.builder()
                .nroDocumento("v1234")
                .status("Creado")
                .build();
    }

}

package com.reba.challenge.adapter.jdbc.model;

import com.reba.challenge.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "PERSONA")
public class PersonaEntityModel {

    @Id
    @Column(name = "nro_documento", nullable = false)
    private String nroDocumento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @OneToMany(mappedBy = "personaEntityModel", cascade = CascadeType.ALL)
    //@JoinColumn(name = "persona_id", nullable = false)
    private List<ContactoEntityModel> contactoEntityModels;

    public static PersonaEntityModel fromDomain(Persona persona) {
        return PersonaEntityModel.builder()
                .nroDocumento(persona.getNroDocumento())
                .nombre(persona.getNombre())
                .edad(persona.getEdad())
                .contactoEntityModels(persona.getContactos().stream().map(map -> ContactoEntityModel.fromDomain(map, persona.getNroDocumento())).collect(Collectors.toList()))
                .build();
    }

    public static Persona toDomain(PersonaEntityModel entityModel) {
        return Persona.builder()
                .nroDocumento(entityModel.getNroDocumento())
                .nombre(entityModel.getNombre())
                .edad(entityModel.getEdad())
                .contactos(entityModel.getContactoEntityModels().stream().map(ContactoEntityModel::toDomain).collect(Collectors.toList()))
                .build();
    }

}

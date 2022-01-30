package com.reba.challenge.adapter.jdbc.model;

import com.reba.challenge.domain.Contacto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "CONTACTO")
public class ContactoEntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "persona_id", nullable = false)
    private String personaFk;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", insertable = false, updatable = false)
    private PersonaEntityModel personaEntityModel;

    public static ContactoEntityModel fromDomain(Contacto contacto, String personaFk) {
        return ContactoEntityModel.builder()
                .nombre(contacto.getNombre())
                .telefono(contacto.getTelefono())
                .personaFk(personaFk)
                .build();
    }

    public static Contacto toDomain(ContactoEntityModel entityModel) {
        return Contacto.builder()
                .nombre(entityModel.getNombre())
                .telefono(entityModel.getTelefono())
                .build();
    }
}

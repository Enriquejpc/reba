package com.reba.challenge.adapter.jdbc;

import com.reba.challenge.adapter.jdbc.model.PersonaEntityModel;
import org.springframework.data.repository.CrudRepository;

public interface PersonaJpaRepository extends CrudRepository<PersonaEntityModel, String> {
}

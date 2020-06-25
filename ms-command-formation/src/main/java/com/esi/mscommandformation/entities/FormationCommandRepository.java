package com.esi.mscommandformation.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FormationCommandRepository extends JpaRepository<Formation,String> {
}

package com.esi.coreapi.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data @AllArgsConstructor @NoArgsConstructor
public class RemoveEtudiantCommand {
    @TargetAggregateIdentifier
    private String idFormation;
    private Long idEtudiant;

}

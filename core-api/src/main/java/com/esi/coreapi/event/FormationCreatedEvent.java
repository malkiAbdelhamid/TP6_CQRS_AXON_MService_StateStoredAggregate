package com.esi.coreapi.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormationCreatedEvent {

    @TargetAggregateIdentifier
    private String idFormation;
    private String nom;
    private int duree;
}

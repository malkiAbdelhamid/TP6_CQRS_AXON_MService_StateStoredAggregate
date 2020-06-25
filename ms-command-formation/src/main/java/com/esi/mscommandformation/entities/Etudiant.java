package com.esi.mscommandformation.entities;

import com.esi.coreapi.command.UpdateEtudiantCommand;
import com.esi.coreapi.event.EtudiantUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Etudiant {

    @Id
    @EntityId
    @EqualsAndHashCode.Include
    private Long idEtudiant;

    private String nom;
    private Date dateN;

    @ManyToOne
    private Formation formation;

    @CommandHandler
    public void on(UpdateEtudiantCommand cmd)
    {
        // consitency rules

        this.nom=(cmd.getNom()!=null) ? cmd.getNom() : this.nom;
        this.dateN=(cmd.getDateN()!=null) ? cmd.getDateN() : this.dateN;

        AggregateLifecycle.apply(new EtudiantUpdatedEvent(
                cmd.getIdEtudiant(),
                cmd.getNom(),
                cmd.getDateN()
        ));
    }

}

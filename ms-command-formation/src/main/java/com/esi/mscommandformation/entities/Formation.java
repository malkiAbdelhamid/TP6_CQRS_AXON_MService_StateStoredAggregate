package com.esi.mscommandformation.entities;

import com.esi.coreapi.command.AddEtudiantCommand;
import com.esi.coreapi.command.CreateFormationCommand;
import com.esi.coreapi.command.RemoveEtudiantCommand;
import com.esi.coreapi.event.EtudiantAddedEvent;
import com.esi.coreapi.event.EtudiantRemovedEvent;
import com.esi.coreapi.event.FormationCreatedEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Aggregate
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Formation {

    @AggregateIdentifier
    @Id
    private String idFormation;
    private String nom;
    private int duree;


    @AggregateMember
   @JsonIgnore
    @OneToMany(mappedBy = "formation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;

    @CommandHandler
    public Formation(CreateFormationCommand cmd)
    {
        // consistency rules

        this.idFormation=cmd.getIdFormation();
        this.nom=cmd.getNom();
        this.duree=cmd.getDuree();
        this.etudiants=new ArrayList<>();

        AggregateLifecycle.apply(new FormationCreatedEvent( cmd.getIdFormation(),
                cmd.getNom(), cmd.getDuree()));

    }

    @CommandHandler
    public void handle(AddEtudiantCommand cmd)
    {
        //consitency rules
        this.etudiants.add(new Etudiant(
                cmd.getIdEtudiant(),
                cmd.getNom(),
                cmd.getDateN(), this
        ));

        AggregateLifecycle.apply(new EtudiantAddedEvent(
              this.nom,
                cmd.getIdEtudiant(),
                cmd.getNom()));


    }


    @CommandHandler
    public void handle(RemoveEtudiantCommand cmd, EtudiantRepository etudiantRepository)
    {
        //constraint
        Etudiant etudiant=new Etudiant();
        etudiant.setIdEtudiant(cmd.getIdEtudiant());
        this.etudiants.remove(etudiant);

        etudiantRepository.deleteById(cmd.getIdEtudiant());

        AggregateLifecycle.apply(new EtudiantRemovedEvent(cmd.getIdEtudiant()));

    }


}
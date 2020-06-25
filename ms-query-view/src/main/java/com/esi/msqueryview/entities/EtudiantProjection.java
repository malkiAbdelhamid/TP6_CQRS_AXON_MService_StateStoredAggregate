package com.esi.msqueryview.entities;

import com.esi.coreapi.event.EtudiantAddedEvent;
import com.esi.coreapi.event.EtudiantRemovedEvent;
import com.esi.coreapi.event.EtudiantUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EtudiantProjection {

    @Autowired
    EtudiantViewRepository etudiantViewRepository;

    @EventHandler
    public void on(EtudiantAddedEvent event)
    {
        etudiantViewRepository.save(new EtudiantView(event.getIdEtudiant(),event.getNom(),event.getNomFormation()));
    }

    @EventHandler
    public void on(EtudiantUpdatedEvent event)
    {
        EtudiantView etudiantView=etudiantViewRepository.findById(event.getIdEtudiant()).get();

        if(event.getNom()!=null) etudiantView.setNom(event.getNom());

        etudiantViewRepository.save(etudiantView);

    }

    @EventHandler
    public void handle(EtudiantRemovedEvent event)
    {
        etudiantViewRepository.deleteById(event.getIdEtudiant());

    }
}

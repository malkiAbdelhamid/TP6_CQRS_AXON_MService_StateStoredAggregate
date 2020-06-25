package com.esi.coreapi.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class EtudiantUpdatedEvent {
    private Long idEtudiant;
    private String nom;
    private Date dateN;
}

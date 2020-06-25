package com.esi.coreapi.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class EtudiantAddedEvent {

    private String nomFormation;
    private Long idEtudiant;
    private String nom;
  }

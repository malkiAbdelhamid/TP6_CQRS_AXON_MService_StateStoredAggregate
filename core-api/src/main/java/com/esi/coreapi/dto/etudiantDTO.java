package com.esi.coreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class etudiantDTO {
    private Long idEtudiant;
    private String nom;
    private Date dateN;
}

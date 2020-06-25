package com.esi.msqueryview.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class EtudiantView {

    @Id
    private Long idEtudiant;
    private String nom;
    private String nomFormation;
}

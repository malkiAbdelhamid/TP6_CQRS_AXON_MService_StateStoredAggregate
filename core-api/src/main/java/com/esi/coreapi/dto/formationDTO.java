package com.esi.coreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class formationDTO {
    private String idFormation;
    private String nom;
    private int duree;
}

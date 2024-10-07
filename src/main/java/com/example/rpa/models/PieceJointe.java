package com.example.rpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceJointe {

    @Id
    private Integer id;

    private String nomFichier;

    private String typeFichier;

    private byte[] contenu ;

    @ManyToOne

    private   EmailMessage emailMessage;


    @ManyToOne
    private ExtracteurPieceJointe extracteurPieceJointe;

}
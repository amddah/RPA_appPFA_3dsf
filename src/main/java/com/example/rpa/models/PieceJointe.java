package com.example.rpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceJointe {

    @Id
    @GeneratedValue
    private Integer id;

    private String nomFichier;

    private String typeFichier;

    private byte[] contenu ;

    @ManyToOne

    private   EmailMessage emailMessage;


    @ManyToOne
    private ExtracteurPieceJointe extracteurPieceJointe;

}
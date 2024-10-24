package com.example.rpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Lob
    @Column(name = "contenu", columnDefinition = "LONGBLOB")
    private byte[] contenu ;

    @ManyToOne()
    @JsonIgnore
    private   EmailMessage emailMessage;


    @ManyToOne
    private ExtracteurPieceJointe extracteurPieceJointe;

}
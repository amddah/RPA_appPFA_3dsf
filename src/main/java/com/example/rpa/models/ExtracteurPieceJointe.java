package com.example.rpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtracteurPieceJointe {

   @Id
   @GeneratedValue
   private Integer id;

   private String nomFichier;

   private Date dateExtraction ;

   @OneToMany(mappedBy = "extracteurPieceJointe", cascade = CascadeType.ALL)
   private  List<PieceJointe> pieceJointes;

}

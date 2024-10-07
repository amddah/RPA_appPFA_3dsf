package com.example.rpa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtracteurPieceJointe {

   @Id
   private Integer id;

   private String nomFichier;

   private Date dateExtraction ;

   @OneToMany(mappedBy = "extracteurPieceJointe", cascade = CascadeType.ALL)
   private  List<PieceJointe> pieceJointes;

}

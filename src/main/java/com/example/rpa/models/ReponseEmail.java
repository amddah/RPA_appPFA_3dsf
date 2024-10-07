package com.example.rpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseEmail {

    @Id
    private Integer id;

    private  String corps;

    private Date dateRepnse;

    @ManyToOne
    private EmailMessage emailMessage;

}

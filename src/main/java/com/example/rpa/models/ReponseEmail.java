package com.example.rpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseEmail {

    @Id
    @GeneratedValue
    private Integer id;

    private  String corps;

    private Date dateRepnse;

    @ManyToOne
    private EmailMessage emailMessage;


    public ReponseEmail(String corps, Date dateRepnse, EmailMessage emailMessage) {
        this.corps = corps;
        this.dateRepnse = dateRepnse;
        this.emailMessage = emailMessage;
    }
}

package com.example.rpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data

public class EmailMessage {

    @Id
    @GeneratedValue
    private Integer idEmailM;
    private String dist;
    private String subject;
    private String message;

    public EmailMessage() {
    }
    public EmailMessage(String to, String subject, String message) {
        this.dist = to;
        this.subject = subject;
        this.message = message;
    }

    @OneToMany(mappedBy = "emailMessage", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PieceJointe> pieceJointeList;

    @OneToMany(mappedBy = "emailMessage", cascade = CascadeType.ALL)
    private List<ReponseEmail> reponses;

}

package com.example.rpa.controller;

import com.example.rpa.models.EmailData;
import com.example.rpa.models.EmailMessage;
import com.example.rpa.models.PieceJointe;
import com.example.rpa.service.EmailSenderService;
import com.example.rpa.service.EmailService;
import com.example.rpa.service.Impl.EmailMessageImpl;
import com.example.rpa.service.Impl.PieceJointeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class EmailControler {

    @Autowired
    private EmailService emailService ;

    @Autowired
    private EmailMessageImpl emailMessage;

    @Autowired
    private PieceJointeImpl pieceJointe;

    @GetMapping("/emails")
    public List<EmailMessage> getEmaills(){

        return emailMessage.findAll();
    }
    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }


    @GetMapping("/pieces")
    public List<PieceJointe> getPieces(){

       return pieceJointe.findAll();

    }



}

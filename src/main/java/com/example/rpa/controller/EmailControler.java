package com.example.rpa.controller;

import com.example.rpa.models.EmailData;
import com.example.rpa.models.EmailMessage;
import com.example.rpa.service.EmailSenderService;
import com.example.rpa.service.EmailService;
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
    private EmailSenderService emailSenderService;
    @GetMapping("/s")
    public ResponseEntity getEmails(Model model) throws MessagingException , IOException {

            emailService.checkEmails();

        List<EmailData> emails = emailService.checkEmails();
        model.addAttribute("emails", emails);


        //emailSenderService.sendEmail(new EmailMessage("abdelkbir.amddah@gmail.com","abdelkbir.amddah@gmail.com",  "this is a test"));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }

}

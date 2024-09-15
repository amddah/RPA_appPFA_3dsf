package com.example.rpa.controller;

import com.example.rpa.models.EmailData;
import com.example.rpa.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
public class EmailControler {

    @Autowired
    private EmailService emailService ;

    @GetMapping("/")
    public ResponseEntity getEmails(Model model) throws MessagingException , IOException {

            emailService.checkEmails();

        List<EmailData> emails = emailService.checkEmails();
        model.addAttribute("emails", emails);



        return ResponseEntity.ok().build();
    }
}

package com.example.rpa.controller;

import com.example.rpa.models.EmailData;
import com.example.rpa.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Controller
public class EmailControler {

    @Autowired
    private EmailService emailService ;

    @GetMapping("/")
    public String getEmails(Model model) throws MessagingException , IOException {

            emailService.checkEmails();

        List<EmailData> emails = emailService.checkEmails(); // Modifiez le service pour retourner une liste d'emails
        model.addAttribute("emails", emails);



        return "index";
    }
}

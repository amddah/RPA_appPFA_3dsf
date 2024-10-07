package com.example.rpa.service;

import com.example.rpa.models.EmailData;
import com.example.rpa.models.EmailMessage;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailSenderService {


    private final RestTemplate restTemplate = new RestTemplate();

    public void sendEmail(EmailMessage emailMessage) {
        String url = "http://localhost:8085/send-mail";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EmailMessage> request = new HttpEntity<>(emailMessage, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // Utiliser la réponse pour envoyer l'email
            if (response.getStatusCode() == HttpStatus.OK) {
                String generatedResponse = response.getBody();
                send(generatedResponse, emailMessage.getDist(), emailMessage.getSubject());
            } else {
                // Gérer le cas où le statut n'est pas OK
                System.out.println("Erreur lors de la génération de la réponse email : " + response.getStatusCode());
            }
        } catch (HttpServerErrorException e) {
            // Gérer les exceptions 500 du serveur distant
            System.out.println("Erreur de serveur lors de la génération de la réponse : " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // Gérer toutes les autres exceptions
            e.printStackTrace();
        }
    }


    private void send(String emailContent, String to, String subject) {
        // Logique d'envoi de l'email ici
        System.out.println("Envoi de l'email à: " + to);
        System.out.println("Sujet: " + subject);
        System.out.println("Contenu: " + emailContent);
    }
}

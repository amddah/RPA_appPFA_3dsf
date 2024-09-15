package com.example.rpa.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GptService {

    @Value("${flask.api.url}")  // URL de votre API Flask
    private String apiUrl;

    public String generateResponse(String emailContent) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Définir le type de contenu comme JSON

        // Construire le corps de la requête JSON
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", emailContent); // Assurez-vous que la clé correspond à ce que l'API Flask attend
        System.out.println(requestBody);
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        try {
            // Envoyer la requête POST à l'API Flask
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
            System.out.println("API Flask Response: " + response.getBody());
            return response.getBody(); // Retourner la réponse brute pour voir le résultat
        } catch (HttpClientErrorException e) {
            System.out.println("Error: " + e.getMessage());
            return "Error in calling Flask API";
        }
    }

}

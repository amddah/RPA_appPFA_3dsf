package com.example.rpa.service;

import org.springframework.stereotype.Service;

@Service
public class TextCleanerService {

    public String cleanEmailContent(String emailContent) {
        // Enlever les lignes d'en-tête typiques des emails transférés ou répondus
        String cleanedContent = emailContent.replaceAll("(?i)---------- Forwarded message ---------", "");
        cleanedContent = cleanedContent.replaceAll("(?i)Subject:.*", "");
        cleanedContent = cleanedContent.replaceAll("(?i)From:.*", "");
        cleanedContent = cleanedContent.replaceAll("(?i)To:.*", "");
        cleanedContent = cleanedContent.replaceAll("(?i)Date:.*", "");

        // Supprimer les lignes vides et les espaces en trop
        cleanedContent = cleanedContent.trim().replaceAll("(?m)^[ \t]*\r?\n", "");

        return cleanedContent;
    }
}

package com.example.rpa.service;

import org.springframework.stereotype.Service;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class ExractionTextServices {
    //   méthode pour extraire le texte des messages
    public String extractTextFromMessage(MimeMessage message) throws MessagingException, IOException {
        StringBuilder textContent = new StringBuilder();

        if (message.isMimeType("text/plain")) {
            // Si le contenu est directement du texte brut
            textContent.append(message.getContent().toString());
        } else if (message.isMimeType("multipart/*")) {
            // Si le contenu est de type multipart (contient plusieurs parties)
            Multipart multipart = (Multipart) message.getContent();

            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);

                if (bodyPart.isMimeType("text/plain")) {
                    // Extraire le texte brut
                    textContent.append(bodyPart.getContent().toString());
                } else if (bodyPart.isMimeType("multipart/ALTERNATIVE")) {
                    // Gérer le type multipart/ALTERNATIVE qui contient des versions alternatives du contenu (ex. HTML, texte)
                    Multipart altPart = (Multipart) bodyPart.getContent();
                    for (int j = 0; j < altPart.getCount(); j++) {
                        BodyPart altBodyPart = altPart.getBodyPart(j);
                        if (altBodyPart.isMimeType("text/plain")) {
                            // Prendre la version texte brut si elle est disponible
                            textContent.append(altBodyPart.getContent().toString());
                            break; // On prend souvent la première version en texte
                        }
                    }
                }
            }
        }
        return textContent.toString();
    }
}

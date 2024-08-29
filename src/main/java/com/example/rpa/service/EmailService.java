package com.example.rpa.service;

import com.example.rpa.models.EmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.Address;
import javax.mail.BodyPart;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;
      List<EmailData> emailDataList = new ArrayList<>();

    public List<EmailData>  checkEmails() throws MessagingException, IOException {
        // Get the default Session object

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");
        props.put("mail.imaps.ssl.protocols", "TLSv1.2"); // Force TLS v1.2 ou supérieur

        Session session = Session.getInstance(props);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", "votre email", "mots de passe de application");

        // Open the inbox folder
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Get the list of unread messages
        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

        for (Message message : messages) {
            if (message instanceof MimeMessage) {
                MimeMessage mimeMessage = (MimeMessage) message;
                String subject = mimeMessage.getSubject();
                Address[] fromAddress = mimeMessage.getFrom();
                String from = fromAddress[0].toString();
                System.out.println("Email from: " + from);
                System.out.println("Subject: " + subject);
                EmailData emailData = new EmailData(subject, from, new ArrayList<>(), "");
                // Check for multipart content
                if (message.getContent() instanceof Multipart) {
                    Multipart multipart = (Multipart) message.getContent();

                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);

                        if (bodyPart.isMimeType("text/plain")) {
                            String text = (String) bodyPart.getContent();
                            System.out.println("Text: " + text);
                            emailData.setText(text);

                        } else if (bodyPart.isMimeType("multipart/ALTERNATIVE")) {
                            // Handle multipart/ALTERNATIVE
                            Multipart altPart = (Multipart) bodyPart.getContent();
                            for (int j = 0; j < altPart.getCount(); j++) {
                                BodyPart altBodyPart = altPart.getBodyPart(j);
                                if (altBodyPart.isMimeType("text/plain")) {
                                    String text = (String) altBodyPart.getContent();
                                    System.out.println("Text from multipart/ALTERNATIVE: " + text);
                                    emailData.setText(text);
                                    break; // Usually, the plain text is first, so we can break once found.
                                }
                            }

                        } else if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                            String fileName = bodyPart.getFileName();
                            File file = new File("C:\\Users\\hp\\OneDrive\\Desktop\\workspace\\PFA\\dossier" + fileName);
                            try (FileOutputStream output = new FileOutputStream(file)) {
                                output.write(bodyPart.getInputStream().readAllBytes());
                            }
                            System.out.println("Attachment saved: " + file.getAbsolutePath());
                        }
                    }
                }
                emailDataList.add(emailData);
            }
        }


        inbox.close(false);
        store.close();

        return emailDataList;
    }
}

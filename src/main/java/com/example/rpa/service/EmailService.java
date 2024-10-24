package com.example.rpa.service;

import com.example.rpa.models.EmailData;
import com.example.rpa.models.EmailMessage;
import com.example.rpa.models.ExtracteurPieceJointe;
import com.example.rpa.models.PieceJointe;
import com.example.rpa.service.Impl.EmailMessageImpl;
import com.example.rpa.service.Impl.ExtracteurImpl;
import com.example.rpa.service.Impl.PieceJointeImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    private String chemin;
    @Autowired
    private GptService gptService;
    @Autowired
     private ExtracteurImpl extracteurPieceJointe;
    @Autowired
    private TextCleanerService textCleanerService;
    @Autowired
    private  EmailSenderService emailSenderService;

    @Autowired
    private EmailMessageImpl emailMessage;

    @Autowired
    private PieceJointeImpl pieceJointe;
      List<EmailData> emailDataList = new ArrayList<>();

    @Scheduled(fixedRate = 600000000)
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
        store.connect("imap.gmail.com", "abdelkbir.amddah@gmail.com", "zksb pncc gnsj llco");

        // Open the inbox folder
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Get the list of unread messages
        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        EmailMessage emailMessage1 =new EmailMessage();
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
                                    String cleanedContent = textCleanerService.cleanEmailContent(text);
                                    String gptResponse = gptService.generateResponse(cleanedContent);
                                    JsonObject jsonObject = JsonParser.parseString(gptResponse).getAsJsonObject();
                                    System.out.println(jsonObject.get("response").getAsString());
                                    emailSenderService.sendEmail(new EmailMessage(from,subject,jsonObject.get("response").getAsString()));

                                    emailMessage1.setDist(from);
                                    emailMessage1.setMessage(text);
                                    emailMessage1.setSubject(subject);
                                    emailMessage.create(emailMessage1);
                                    break; // Usually, the plain text is first, so we can break once found.
                                }
                            }

                        } else if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                            String fileName = bodyPart.getFileName();
                            //pour cree un pice jointe dans base donnee
                            PieceJointe pieceJointe1 =new PieceJointe();
                            File file = new File(chemin + fileName);
                            try (FileOutputStream output = new FileOutputStream(file)) {
                                output.write(bodyPart.getInputStream().readAllBytes());
                                pieceJointe1.setContenu(bodyPart.getInputStream().readAllBytes());
                                pieceJointe1.setNomFichier(fileName);
                                Path path = Paths.get(chemin+ fileName);
                                String mimeType = Files.probeContentType(path);
                                pieceJointe1.setTypeFichier(mimeType);

                            }
                            System.out.println("Attachment saved: " + file.getAbsolutePath());
                            ExtracteurPieceJointe  extracteurPieceJointe1 =new ExtracteurPieceJointe();
                            extracteurPieceJointe1.setDateExtraction(new Date());
                            extracteurPieceJointe1.setNomFichier(fileName);

                            pieceJointe1.setExtracteurPieceJointe(extracteurPieceJointe1);
                            pieceJointe1.setEmailMessage(emailMessage1);
                            extracteurPieceJointe.create(extracteurPieceJointe1);
                            pieceJointe.create(pieceJointe1);

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

package com.example.rpa.models;

import java.util.List;

public class EmailData {

    private String subject;
    private String from;
    private List<String> attachments;
    private String text;

    public EmailData(String subject, String from, List<String> attachments, String text) {
        this.subject = subject;
        this.from = from;
        this.attachments = attachments;
        this.text = text;
    }

    // Getters et setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

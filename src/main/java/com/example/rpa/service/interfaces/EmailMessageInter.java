package com.example.rpa.service.interfaces;

import com.example.rpa.models.EmailMessage;

public interface EmailMessageInter {

    EmailMessage create(EmailMessage emailMessage);

    EmailMessage update(EmailMessage  emailMessage,Integer id);

    EmailMessage findById(Integer id);

    void delete(Integer id);
}

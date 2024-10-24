package com.example.rpa.service.Impl;

import com.example.rpa.Repository.EmailMessageRepository;
import com.example.rpa.models.EmailMessage;
import com.example.rpa.service.interfaces.EmailMessageInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailMessageImpl implements EmailMessageInter {
    
    @Autowired
    private EmailMessageRepository emailMessageRepository;
    @Override
    public EmailMessage create(EmailMessage emailMessage) {
        return emailMessageRepository.save(emailMessage);
    }

    @Override
    public EmailMessage update(EmailMessage emailMessage, Integer id) {

        Optional<EmailMessage> emailMessageOptional =emailMessageRepository.findById(id);
        if (emailMessageOptional.isPresent()){
            return emailMessageRepository.save(emailMessage);
        }else{
            throw new RuntimeException("Not found");
        }

    }

    @Override
    public EmailMessage findById(Integer id) {
        return emailMessageRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {

        emailMessageRepository.deleteById(id);
    }

    public List<EmailMessage> findAll(){
        return this.emailMessageRepository.findAll();
    }
}

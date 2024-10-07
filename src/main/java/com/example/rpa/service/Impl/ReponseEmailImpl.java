package com.example.rpa.service.Impl;

import com.example.rpa.Repository.ReponseEmailRepository;
import com.example.rpa.models.ReponseEmail;
import com.example.rpa.service.interfaces.ReponseEmailInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReponseEmailImpl implements ReponseEmailInter {
    @Autowired
    private ReponseEmailRepository reponseEmailRepository;
    @Override
    public ReponseEmail create(ReponseEmail reponseEmail) {
        return reponseEmailRepository.save(reponseEmail);
    }

    @Override
    public ReponseEmail update(ReponseEmail reponseEmail, Integer id) throws Exception{

        Optional<ReponseEmail> reponseEmailOptional = reponseEmailRepository.findById(id);

        if (reponseEmailOptional.isPresent()){
            return reponseEmailRepository.save(reponseEmail);
        }else {
            throw new Exception("Email Not found");
        }

    }

    @Override
    public ReponseEmail findById(Integer id) {
        return reponseEmailRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {

        reponseEmailRepository.deleteById(id);
    }

    @Override
    public List<ReponseEmail> findAll() {
        return reponseEmailRepository.findAll();
    }


}

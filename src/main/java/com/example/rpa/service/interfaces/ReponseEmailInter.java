package com.example.rpa.service.interfaces;


import com.example.rpa.models.ReponseEmail;

import java.util.List;

public interface ReponseEmailInter {

    ReponseEmail create(ReponseEmail extracteurPieceJointe);

    ReponseEmail update(ReponseEmail extracteurPieceJointe,Integer id) throws Exception;

    ReponseEmail findById(Integer id);

    void delete(Integer id);

    List<ReponseEmail> findAll();
}

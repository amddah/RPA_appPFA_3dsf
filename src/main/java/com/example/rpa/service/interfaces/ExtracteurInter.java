package com.example.rpa.service.interfaces;

import com.example.rpa.models.ExtracteurPieceJointe;

public interface ExtracteurInter {

    ExtracteurPieceJointe create(ExtracteurPieceJointe extracteurPieceJointe);

    ExtracteurPieceJointe update(ExtracteurPieceJointe extracteurPieceJointe,Integer id);

   ExtracteurPieceJointe findById(Integer id);

   void delete(Integer id);
}

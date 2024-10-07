package com.example.rpa.service.interfaces;


import com.example.rpa.models.PieceJointe;

public interface PieceJointeInter {

     PieceJointe create( PieceJointe extracteurPieceJointe);

      PieceJointe update( PieceJointe extracteurPieceJointe,Integer id);

     PieceJointe findById(Integer id);

    void delete(Integer id);
}

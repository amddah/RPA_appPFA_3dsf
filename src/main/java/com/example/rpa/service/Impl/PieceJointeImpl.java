package com.example.rpa.service.Impl;

import com.example.rpa.Repository.PieceJointeRepository;
import com.example.rpa.models.PieceJointe;
import com.example.rpa.service.interfaces.PieceJointeInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PieceJointeImpl implements PieceJointeInter {
    @Autowired
    PieceJointeRepository pieceJointeRepository;
    @Override
    public PieceJointe create(PieceJointe pieceJointe) {
        return pieceJointeRepository.save(pieceJointe);
    }

    @Override
    public PieceJointe update(PieceJointe pieceJointe, Integer id) {

        Optional<PieceJointe> pieceJointeOptional =pieceJointeRepository.findById(id);
        if (pieceJointeOptional.isPresent()){
            return pieceJointeRepository.save(pieceJointe);
        }else {
            return pieceJointeRepository.save(pieceJointe);
        }

    }

    @Override
    public PieceJointe findById(Integer id) {
        return pieceJointeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {

        pieceJointeRepository.deleteById(id);
    }

    public List<PieceJointe> findAll(){
        return pieceJointeRepository.findAll();
    }
}

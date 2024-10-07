package com.example.rpa.service.Impl;

import com.example.rpa.Repository.ExtracteurRepository;
import com.example.rpa.models.ExtracteurPieceJointe;
import com.example.rpa.service.interfaces.ExtracteurInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtracteurImpl implements ExtracteurInter {

    @Autowired
    private ExtracteurRepository extracteurRepository;
    @Override
    public ExtracteurPieceJointe create(ExtracteurPieceJointe extracteurPieceJointe) {
        return extracteurRepository.save(extracteurPieceJointe);
    }

    @Override
    public ExtracteurPieceJointe update(ExtracteurPieceJointe extracteurPieceJointe, Integer id) {

        Optional<ExtracteurPieceJointe> extracteurPieceJointeOptional = extracteurRepository.findById(id);

        if (extracteurPieceJointeOptional.isPresent()){
            return extracteurRepository.save(extracteurPieceJointe);
        }else
        return extracteurRepository.save(extracteurPieceJointe);
    }

    @Override
    public ExtracteurPieceJointe findById(Integer id) {
        return extracteurRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        extracteurRepository.deleteById(id);
    }

    public List<ExtracteurPieceJointe> findAll(){
        return extracteurRepository.findAll();
    }
}

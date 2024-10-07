package com.example.rpa.Repository;

import com.example.rpa.models.ExtracteurPieceJointe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtracteurRepository extends JpaRepository<ExtracteurPieceJointe,Integer> {
}

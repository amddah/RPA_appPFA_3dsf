package com.example.rpa.Repository;

import com.example.rpa.models.ReponseEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseEmailRepository extends JpaRepository<ReponseEmail,Integer> {
}

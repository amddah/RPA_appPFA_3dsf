package com.example.rpa.Repository;

import com.example.rpa.models.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessage,Integer> {
}

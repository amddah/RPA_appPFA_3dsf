package com.example.rpa.Repository;

import com.example.rpa.models.ConfigApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigAppRepository extends JpaRepository<ConfigApp,Integer> {
}

package com.example.rpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigApp {

    @Id
    @GeneratedValue
    private Integer configID;

    private String Email;
    private String appPassword;
}

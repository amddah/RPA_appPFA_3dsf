package com.example.rpa.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

   @Id
   private String email;

   private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;

    private boolean email_verified;
    private String locale;
}

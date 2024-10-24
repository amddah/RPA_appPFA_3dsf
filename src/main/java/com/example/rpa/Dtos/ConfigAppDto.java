package com.example.rpa.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ConfigAppDto {
    private String email;
    private String appPassword;

}

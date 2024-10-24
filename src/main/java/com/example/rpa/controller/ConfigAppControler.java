package com.example.rpa.controller;

import com.example.rpa.Dtos.ConfigAppDto;
import com.example.rpa.Repository.ConfigAppRepository;
import com.example.rpa.models.ConfigApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigAppControler {

    @Autowired
    private ConfigAppRepository configAppRepository;

    @PostMapping("/config")
    public ConfigApp Configuer(@RequestBody ConfigAppDto configAppDto){

        ConfigApp configApp =new ConfigApp();

        configApp.setAppPassword(configAppDto.getAppPassword());
        configApp.setEmail(configAppDto.getEmail());
        System.out.println(configApp);
        return this.configAppRepository.save(configApp);
    }
}

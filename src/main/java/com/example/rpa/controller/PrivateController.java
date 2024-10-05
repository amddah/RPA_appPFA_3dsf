package com.example.rpa.controller;

import com.example.rpa.Dtos.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class PrivateController {

    @GetMapping("/messages")
    public ResponseEntity<MessageDto> privateMessage(

        @AuthenticationPrincipal(expression = "name") String name){

        System.out.println("name"+name);
        return ResponseEntity.ok(new MessageDto("private content "+name));

    }


    @GetMapping("/public")
    public ResponseEntity<MessageDto> publicContnet(){

        return  ResponseEntity.ok(new MessageDto("hello from ppppp"));
    }
}

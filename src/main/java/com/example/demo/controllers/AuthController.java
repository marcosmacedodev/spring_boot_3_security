package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.models.dto.NewUserDto;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    
    @Autowired
    private UserService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<Void> registrar(@Valid @RequestBody NewUserDto novoUsuarioDto){
        User usuario = usuarioService.insert(novoUsuarioDto);
        return ResponseEntity.created(null).build();
    }
}

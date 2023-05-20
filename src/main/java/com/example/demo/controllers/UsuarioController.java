package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dto.NovoUsuarioDto;
import com.example.demo.models.dto.UsuarioDto;
import com.example.demo.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UsuarioDto> lerTodos(){
      return service.lerTodos();
    }

    @PostMapping
    public void criar(@Valid @RequestBody NovoUsuarioDto novoUsuarioDto){
        service.inserir(novoUsuarioDto);
    }
    
}

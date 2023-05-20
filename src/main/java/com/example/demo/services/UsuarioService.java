package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Usuario;
import com.example.demo.models.dto.NovoUsuarioDto;
import com.example.demo.models.dto.UsuarioDto;
import com.example.demo.repositories.PerfilRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.enums.EPerfil;
import com.example.demo.services.exceptions.UsuarioException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PerfilRepository pRepository;

    @Transactional(readOnly = true)
    public List<UsuarioDto> lerTodos(){
        List <Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(usuario -> new UsuarioDto(usuario)).toList();
    }

    @Transactional
    public Usuario inserir(NovoUsuarioDto novoUsuarioDto){
        Usuario novoUsuario;
        if (repository.existsByUsername(novoUsuarioDto.getUsername())){
            throw new UsuarioException("Nome de usuário '" + novoUsuarioDto.getUsername() + "' já está em uso");
        }
        if (repository.existsByEmail(novoUsuarioDto.getEmail())){
            throw new UsuarioException("Email '" + novoUsuarioDto.getEmail() + "' já está em uso");
        }
        novoUsuario= new Usuario(null, novoUsuarioDto.getUsername(), novoUsuarioDto.getPassword(), novoUsuarioDto.getEmail());
        novoUsuario.addPerfil(EPerfil.ROLE_USER);
        novoUsuario = repository.save(novoUsuario);
        pRepository.saveAll(novoUsuario.getPerfis());
        return novoUsuario;
    }
    
}

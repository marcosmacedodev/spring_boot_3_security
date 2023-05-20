package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.User;
import com.example.demo.models.dto.NewUserDto;
import com.example.demo.models.dto.UserDto;
import com.example.demo.models.enums.EPerfil;
import com.example.demo.repositories.PerfilRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.exceptions.UserNotFoundException;
import com.example.demo.services.exceptions.UserRegisterException;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository pRepository;

    @Transactional(readOnly = true)
    public List<UserDto> findAll(){
        List <User> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> new UserDto(usuario)).toList();
    }

    @Transactional
    public User findById(Long id){
        Optional<User> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new UserNotFoundException("Não foi possível encontrar id: " + id));
    }

    @Transactional
    public User insert(NewUserDto novoUsuarioDto){
        User novoUsuario;
        if (usuarioRepository.existsByUsername(novoUsuarioDto.getUsername())){
            throw new UserRegisterException("Nome de usuário '" + novoUsuarioDto.getUsername() + "' já está em uso");
        }
        if (usuarioRepository.existsByEmail(novoUsuarioDto.getEmail())){
            throw new UserRegisterException("Email '" + novoUsuarioDto.getEmail() + "' já está em uso");
        }
        novoUsuario= new User(null, novoUsuarioDto.getUsername(), novoUsuarioDto.getPassword(), novoUsuarioDto.getEmail());
        novoUsuario.addPerfil(EPerfil.ROLE_USER);
        novoUsuario = usuarioRepository.save(novoUsuario);
        pRepository.saveAll(novoUsuario.getPerfis());
        return novoUsuario;
    }
    
}

package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.User;
import com.example.demo.models.dto.NewUserDto;
import com.example.demo.models.dto.UserDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.UserNotFoundException;
import com.example.demo.services.exceptions.UserRegisterException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto> findAll(){
        List <User> usuarios = userRepository.findAll();
        return usuarios.stream().map(usuario -> new UserDto(usuario)).toList();
    }

    @Transactional
    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("Não foi possível encontrar id: " + id));
    }

    @Transactional
    public User insert(NewUserDto newUserDto){
        User newUser;
        if (userRepository.existsByUsername(newUserDto.getUsername())){
            throw new UserRegisterException("Nome de usuário '" + newUserDto.getUsername() + "' já está em uso");
        }
        if (userRepository.existsByEmail(newUserDto.getEmail())){
            throw new UserRegisterException("Email '" + newUserDto.getEmail() + "' já está em uso");
        }
        newUser = new User(null, newUserDto.getUsername(), newUserDto.getPassword(), newUserDto.getEmail());
        newUser = userRepository.save(newUser);
        return newUser;
    }
    
}

package com.example.demo.models.dto;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.example.demo.models.Role;
import com.example.demo.models.enums.ERole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NewUserDto {
    
    @NotBlank(message = "Preenchimento obrigatório")
    @Length(min = 8, max = 32, message = "Digite um nome de usuário entre 8 e 32 caracteres.")
    private String username;
    @NotBlank(message = "Preenchimento obrigatório")
    @Length(min = 8, max = 12, message = "Digite uma senha entre 8 e 12 caracteres.")
    private String password;
    @NotBlank(message = "Preenchimento obrigatório")
    @Email(message = "Email é inválido")
    private String email;

    private Set<Role> roles = new HashSet<>();

    public NewUserDto() {
        
    }
    
    public NewUserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void addRole(ERole eRole){
        addRole(new Role(null, eRole));
    }

    public void addRole(String name){
        addRole(ERole.valueOf(name));
    }
}

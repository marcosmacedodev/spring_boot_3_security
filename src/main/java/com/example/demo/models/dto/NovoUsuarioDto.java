package com.example.demo.models.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NovoUsuarioDto {
    
    @NotBlank(message = "Preenchimento obrigatório")
    @Length(min = 8, max = 32, message = "Digite um nome de usuário entre 8 e 32 caracteres.")
    private String username;
    @NotBlank(message = "Preenchimento obrigatório")
    @Length(min = 8, max = 12, message = "Digite uma senha entre 8 e 12 caracteres.")
    private String password;
    @NotBlank(message = "Preenchimento obrigatório")
    @Email(message = "Email é inválido")
    private String email;

    public NovoUsuarioDto() {
    }
    
    public NovoUsuarioDto(String username, String password, String email) {
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
}

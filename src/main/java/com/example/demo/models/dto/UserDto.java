package com.example.demo.models.dto;

import com.example.demo.models.User;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    public UserDto() {
    }
    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserDto(User usuario){
        id = usuario.getId();
        username = usuario.getUsername();
        email = usuario.getEmail();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

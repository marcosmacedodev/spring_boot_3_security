package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    
}

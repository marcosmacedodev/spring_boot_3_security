package com.example.demo.securities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.User;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(BAuthentication.class);

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("");
        User usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " não foi encontrado."));
        
        UserDetailsImpl user = new UserDetailsImpl(usuario.getId(), 
        usuario.getUsername(), 
        usuario.getPassword(), 
        usuario.getPerfis().stream().map(
            perfil -> new SimpleGrantedAuthority(perfil.getNome().name())).toList());
        return user;
    }
}

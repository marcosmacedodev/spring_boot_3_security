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
import com.example.demo.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

    @Autowired
    private UserRepository usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(BAuthentication.class);

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("");
        User user = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " não foi encontrado."));
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user.getId(), 
        user.getUsername(), 
        user.getPassword(), 
        user.getRoles().stream().map(
            role -> new SimpleGrantedAuthority(role.getNome().name())).toList());
        return userDetailsImpl;
    }
}

package com.example.demo.securities;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BAuthentication extends BasicAuthenticationFilter {

    public BAuthentication(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;



    private static final Logger logger = LoggerFactory.getLogger(BAuthentication.class);

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

                logger.info("");
        String authString = request.getHeader("Authorization");

        try {

            if (authString != null && authString.startsWith("Basic"))
            {
                String token = authString.substring("Basic".length()).trim();
                String username = getUsernamePassword(token).get("username");
                String password = getUsernamePassword(token).get("password");
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                if (userDetails.getPassword().equals(password)){
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(userDetails,
                                                                null,
                                                                userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        catch (Exception e) 
        {
            
        }
        chain.doFilter(request, response);
    }

    private Map<String, String> getUsernamePassword(String token){
        Map<String, String> map;
        byte [] bytes = Base64.getDecoder().decode(token);
        String s = new String(bytes);
        String [] arrString =  s.split(":");
        if (arrString.length >= 2){
            map = new HashMap<>();
            map.put("username", arrString[0]);
            map.put("password", arrString[1]);
            return map;
        }
        throw new RuntimeException("Não foi possível resgatar usuário e senha.");
    }
}

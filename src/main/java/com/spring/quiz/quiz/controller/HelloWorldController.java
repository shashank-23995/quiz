package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.configuration.JwtRequestFilter;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_CANDIDATE')")
public class HelloWorldController {
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Value("${jwt.secret}")
    private String secret;
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping("/greet")
    public String greetUser(@RequestHeader(name = "Authorization") String token) {
        String jwtToken = token.substring(7);
        final Claims claims = getAllClaimsFromToken(jwtToken);
        System.out.println(JwtRequestFilter.token_username.get());
        System.out.println(JwtRequestFilter.token_role.get());
        if(JwtRequestFilter.token_role.get().equals("ROLE_ADMIN")){
            return "Hello Admin";
        }

        if(JwtRequestFilter.token_role.get().equals("ROLE_CANDIDATE")){
            return "Hello Candidate";
        }
        return "";
    }


}
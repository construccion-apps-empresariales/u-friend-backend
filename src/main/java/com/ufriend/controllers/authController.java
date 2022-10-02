package com.ufriend.controllers;

import com.ufriend.dto.LoginDTO;
import com.ufriend.dto.TokenDTO;
import com.ufriend.role.RoleEntity;
import com.ufriend.user.UserDao;
import com.ufriend.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class authController {

    @Autowired
    UserDao userDao;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        UserEntity user = userDao.findByEmail(loginDTO.getEmail());
        if (user == null){
            return ResponseEntity.badRequest().body("Invalid Email or Password.");
        }
        RoleEntity role = user.getRole();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(getAccessToken(user.getName(), role.getName()));
        tokenDTO.setRefreshToken(getRefreshToken(user.getName(), role.getName()));
        return ResponseEntity.ok(tokenDTO);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok("Session closed");
    }

    private String getAccessToken(String username, String role) {
        String secretKey = "my-super-secret-key";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);
        String token = Jwts
                .builder()
                .setId("U-Friend")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }

    private String getRefreshToken(String username, String role) {
        String secretKey = "my-super-secret-key";
        String token = Jwts
                .builder()
                .setId("U-Friend")
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}

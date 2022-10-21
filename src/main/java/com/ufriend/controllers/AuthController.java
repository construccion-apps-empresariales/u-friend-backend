package com.ufriend.controllers;

import com.ufriend.config.auth.TokenHandle;
import com.ufriend.dto.auth.LoginDTO;
import com.ufriend.dto.auth.RegisterInDTO;
import com.ufriend.dto.http.ResponseDTO;
import com.ufriend.dto.http.TokenDTO;
import com.ufriend.role.RoleEntity;
import com.ufriend.user.UserEntity;
import com.ufriend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenHandle tokenHandle;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterInDTO body){
        
        if (!body.getPassword().equals(body.getConfirmPassword())){
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(false, "Password and Confirm Password does not match", null));
        }

        if (userService.findByEmail(body.getEmail()) != null){
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseDTO(false, "Email already taken", null));
        }
        
        UserEntity user = new UserEntity();
        user.setEmail(body.getEmail());
        user.setPassword(body.getPassword());
        userService.save(user);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDTO(true, "User registered", user)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO body){
        UserEntity user = userService.findByEmail(body.getEmail());
        if (user == null){
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(false, "Invalid Email or Password.", null)
            );
        }
        RoleEntity role = user.getRole();
        TokenDTO token = new TokenDTO();
        token.setAccessToken(tokenHandle.getAccessToken(user.getName(), role.getName()));
        token.setRefreshToken(tokenHandle.getRefreshToken(user.getName(), role.getName()));
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDTO(true, "Login success", token));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDTO(true, "Session closed", null));
    }

}

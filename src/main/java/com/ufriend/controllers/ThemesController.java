package com.ufriend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufriend.dto.ResponseDTO;
import com.ufriend.theme.ThemeDao;
import com.ufriend.theme.ThemeEntity;
import com.ufriend.theme.ThemeService;

@RestController()
public class ThemesController {
    
    @Autowired
    ThemeDao themeDao;

    @Autowired
    ThemeService themeService;

    @GetMapping("/themes")
    public ResponseEntity<ResponseDTO> getAll() {
        Iterable<ThemeEntity> themes = themeDao.findAll();
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDTO(true, null, themes));
    }

}

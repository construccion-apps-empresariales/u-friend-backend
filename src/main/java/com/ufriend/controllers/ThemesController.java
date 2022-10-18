package com.ufriend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ufriend.dto.ResponseDTO;
import com.ufriend.theme.ThemeEntity;
import com.ufriend.theme.ThemeService;

@RestController()
public class ThemesController {

    @Autowired
    ThemeService themeService;

    @GetMapping("/themes")
    public ResponseEntity<ResponseDTO> getAll() {
        Iterable<ThemeEntity> themes = themeService.list();
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDTO(true, null, themes));
    }

    @GetMapping("/theme/{id}")
    public ResponseEntity<ResponseDTO> getOne(@PathVariable(name = "id") String id) {
        ThemeEntity theme = themeService.findById(id);
        if (theme == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(true, String.format("Theme not with id '%s' found", id), null));
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDTO(true, null, theme));
    }


}

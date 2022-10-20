package com.ufriend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ufriend.dto.http.ResponseDTO;
import com.ufriend.theme.ThemeEntity;
import com.ufriend.theme.ThemeService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController()
public class ThemesController extends ExceptionHandlerController {

    @Autowired
    ThemeService themeService;

    // Get all themes
    @GetMapping("/themes")
    public ResponseEntity<ResponseDTO> getAll() {
        Iterable<ThemeEntity> themes = themeService.list();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, themes));
    }

    // Get one theme
    @GetMapping("themes/{id}")
    public ResponseEntity<ResponseDTO> getOne(@PathVariable() String id) {
        ThemeEntity theme = themeService.findById(id);
        if (theme == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(
                            false,
                            String.format("Theme with id '%s' not found", id),
                            null));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, theme));
    }

    // TODO SetProtected
    // Cretae a theme
    @PostMapping("/themes")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody ThemeEntity body) {
        ThemeEntity theme = this.themeService.findById(body.getId());
        if (theme != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(false, "Theme alredy exist's", theme));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Theme created", this.themeService.save(body)));
    }

    // TODO SetProtected
    // Update a theme
    @PutMapping("themes/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable() String id, @Valid @RequestBody ThemeEntity body) {
        ThemeEntity theme = themeService.findById(id);
        if (theme == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(
                            false,
                            String.format("Theme with id '%s' not found", id),
                            null));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        true,
                        "Theme updated",
                        this.themeService.save(body)));
    }

}

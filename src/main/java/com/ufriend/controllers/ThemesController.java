package com.ufriend.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    private final String PATH = "/themes";
    private final String PATH_WITH_ID_VARIABLE = "/themes/{id}";

    @Autowired
    ThemeService themeService;

    // TODO Set all methos protected by jwt

    // Get all themes
    @GetMapping(PATH)
    public ResponseEntity<ResponseDTO> getAll() {
        Iterable<ThemeEntity> themes = themeService.list();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, themes));
    }

    // Get one theme
    @GetMapping(PATH_WITH_ID_VARIABLE)
    public ResponseEntity<ResponseDTO> getOne(@PathVariable() String id) {
        ThemeEntity theme = themeService.findById(id);
        if (theme == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(
                            false,
                            this.notFound(id),
                            null));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, theme));
    }

    // TODO SetAuthorized 
    // Cretae a theme
    @PostMapping(PATH)
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

    // TODO SetAuthorized 
    // Update a theme
    @PutMapping(PATH)
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody ThemeEntity body) {
        ThemeEntity theme = themeService.findById(body.getId());
        if (theme == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(
                            false,
                            this.notFound(body.getId()),
                            null));
        }

        body.setUpdatedAt(LocalDateTime.now());
        this.themeService.save(body);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        true,
                        "Theme updated",
                        null));
    }

    // TODO SetAuthorized
    @DeleteMapping(PATH_WITH_ID_VARIABLE)
    public ResponseEntity<ResponseDTO> delete(@PathVariable() String id) {
        ThemeEntity theme = themeService.findById(id);
        if (theme == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(
                            false,
                            this.notFound(id),
                            null));
        }

        theme.setDeletedAt(LocalDateTime.now());
        themeService.save(theme);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    private String notFound(String id) {
        return String.format("Theme with id '%s' not found", id);
    }

}

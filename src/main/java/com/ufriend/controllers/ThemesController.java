package com.ufriend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import com.ufriend.theme.ThemeDao;
import com.ufriend.theme.ThemeService;

@RestController()
public class ThemesController {
    
    @Autowired
    ThemeDao themeDao;

    @Autowired
    ThemeService themeService;

}

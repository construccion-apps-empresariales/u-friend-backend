package com.ufriend.theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeService implements IThemeService {

    @Autowired
    private ThemeDao themeDao;
    
    @Override
public List<ThemeEntity> list() {
        return (ArrayList<ThemeEntity>) themeDao.findAll();
    }

    @Override
    public ThemeEntity findById(String themeId) {
        return themeDao.findById(themeId).orElse(null);
    }

    @Override
    public ThemeEntity save(ThemeEntity theme) {
        return themeDao.save(theme);
    }

    @Override
    public void delete(ThemeEntity theme) {
        themeDao.delete(theme);
    }
}

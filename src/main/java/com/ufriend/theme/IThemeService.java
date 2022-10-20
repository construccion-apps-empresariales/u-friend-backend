package com.ufriend.theme;

import java.util.List;

public interface IThemeService {
    public List<ThemeEntity> list();
    public List<ThemeEntity> listIncludeDeleteds();
    public ThemeEntity findById(String themeId);
    public ThemeEntity findByIdIncludeDeleteds(String themeId);
    public ThemeEntity save(ThemeEntity language);
    public void delete(ThemeEntity language);
}

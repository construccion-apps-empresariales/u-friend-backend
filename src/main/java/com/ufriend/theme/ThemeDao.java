package com.ufriend.theme;

import com.ufriend.language.LanguageEntity;
import org.springframework.data.repository.CrudRepository;

public interface ThemeDao extends CrudRepository<ThemeEntity, String> {
}

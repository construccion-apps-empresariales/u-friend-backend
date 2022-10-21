package com.ufriend.theme;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ThemeDao extends CrudRepository<ThemeEntity, String> {

    @Override
    @Query("SELECT t FROM ThemeEntity t WHERE t.deletedAt IS NULL AND t.id = :id")
    public Optional<ThemeEntity> findById(String id);

    @Override
    @Query("SELECT t FROM ThemeEntity t WHERE t.deletedAt IS NULL")
    public Iterable<ThemeEntity> findAll();

}

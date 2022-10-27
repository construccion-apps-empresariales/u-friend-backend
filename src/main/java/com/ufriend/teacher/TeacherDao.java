package com.ufriend.teacher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherDao extends CrudRepository<TeacherEntity, Long> {

    @Query("select t from TeacherEntity t where t.email = ?1")
    TeacherEntity findByEmail(String email);
}

package com.ufriend.course;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseDao extends CrudRepository<CourseEntity, Long> {

    @Query("from CourseEntity  c where c.user.id = :user")
    List<CourseEntity> findAllCourseByUser(Long user);
}

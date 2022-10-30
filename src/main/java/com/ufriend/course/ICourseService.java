package com.ufriend.course;


import java.util.List;

public interface ICourseService {
    public List<CourseEntity> list();
    public CourseEntity findById(Long courseId);
    public CourseEntity save(CourseEntity course);
    public void delete(CourseEntity course);
}

package com.ufriend.course;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Resource
    private CourseDao courseDao;

    @Override
    public List<CourseEntity> list() {
        return (ArrayList<CourseEntity>) courseDao.findAll();
    }

    @Override
    public CourseEntity findById(Long courseId) {
        return courseDao.findById(courseId).orElse(null);
    }

    @Override
    public CourseEntity save(CourseEntity course) { return courseDao.save(course); }

    @Override
    public void delete(CourseEntity course) {
        // TODO Think about the deletion
    }
}

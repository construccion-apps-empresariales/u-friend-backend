package com.ufriend.course;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<CourseEntity> getUserCourses(Long userId){
        List<CourseEntity> allCourses = this.list();
        List<CourseEntity> userCourses = new ArrayList<>();
        for (CourseEntity course: allCourses) {
            if (course.getUser().getId() != null) {
                if (Objects.equals(course.getUser().getId(), userId)) {
                    userCourses.add(course);
                }
            }
        }
        return userCourses;
    }
}

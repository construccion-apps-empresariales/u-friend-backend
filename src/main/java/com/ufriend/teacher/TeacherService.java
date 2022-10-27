package com.ufriend.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService implements ITeacherService {

    @Resource
    private TeacherDao teacherDao;


    @Override
    public List<TeacherEntity> list() {
        return (ArrayList<TeacherEntity>) teacherDao.findAll();
    }

    public TeacherEntity findByEmail(String email){ return teacherDao.findByEmail(email); }

    @Override
    public TeacherEntity findById(Long teacherId) {
        return teacherDao.findById(teacherId).orElse(null);
    }

    @Override
    public TeacherEntity save(TeacherEntity teacher) { return teacherDao.save(teacher); }

    @Override
    public void delete(TeacherEntity teacher) {
        // TODO Think about the deletion
    }
}

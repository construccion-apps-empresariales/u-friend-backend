package com.ufriend.teacher;

import com.ufriend.theme.ThemeEntity;

import java.util.List;

public interface ITeacherService {
    public List<TeacherEntity> list();
    public TeacherEntity findById(Long teacherId);
    public TeacherEntity save(TeacherEntity teacher);
    public void delete(TeacherEntity teacher);
}

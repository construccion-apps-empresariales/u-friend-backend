package com.ufriend.note;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService implements INoteService {

    @Resource
    private NoteDao noteDao;

    @Override
    public List<NoteEntity> list() {
        return (ArrayList<NoteEntity>) noteDao.findAll();
    }

    @Override
    public NoteEntity findById(Long courseId) {
        return noteDao.findById(courseId).orElse(null);
    }

    @Override
    public NoteEntity save(NoteEntity course) { return noteDao.save(course); }

    @Override
    public void delete(NoteEntity course) {
        // TODO Think about the deletion
    }
}

package com.ufriend.note;


import com.ufriend.note.NoteEntity;

import java.util.List;

public interface INoteService {
    public List<NoteEntity> list();
    public NoteEntity findById(Long noteId);
    public NoteEntity save(NoteEntity note);
    public void delete(NoteEntity note);
}

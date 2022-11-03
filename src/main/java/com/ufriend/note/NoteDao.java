package com.ufriend.note;

import org.springframework.data.repository.CrudRepository;

public interface NoteDao extends CrudRepository<NoteEntity, Long> {

}

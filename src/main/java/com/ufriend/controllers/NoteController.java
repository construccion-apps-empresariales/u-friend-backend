package com.ufriend.controllers;

import com.ufriend.note.NoteEntity;
import com.ufriend.dto.http.ResponseDTO;
import com.ufriend.note.NoteService;
import com.ufriend.teacher.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/notes")
public class NoteController extends ExceptionHandlerController {

    @Resource
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<ResponseDTO> index(){
        List<NoteEntity> notes = this.noteService.list();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Notes list", notes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable Long id){
        NoteEntity course = noteService.findById(id);
        if (course == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Note not found", null));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, course));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody NoteEntity body) {
        NoteEntity savedNote = this.noteService.save(body);
        if (savedNote.getFather() != null) {
            savedNote.setFather(noteService.findById(savedNote.getFather().getId()));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Note created", savedNote));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody NoteEntity body) {
        NoteEntity dbNote = this.noteService.findById(body.getId());

        if (dbNote == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Note not found", null));
        }

        if (body.getFather() != null)
            if (body.getFather().getId() != null)
                if (!body.getFather().getId().equals("")) {
                    NoteEntity father = this.noteService.findById(body.getFather().getId());
                    if (father == null)
                        return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(new ResponseDTO(false, "Father note not found", body.getFather().getId()));
                    else {
                        dbNote.setFather(father);
                    }
                }

        if (body.getName() != null)
            if (!body.getName().equals(""))
                dbNote.setName(body.getName());

        if (body.getPercentage() != 0.0f)
            dbNote.setPercentage(body.getPercentage());

        if (body.getValue() != 0.0f)
            dbNote.setValue(body.getValue());

        if (body.getStarts() != null)
            dbNote.setStarts(body.getStarts());

        if (body.getEnds() != null)
            dbNote.setEnds(body.getEnds());

        dbNote.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Note updated", this.noteService.save(dbNote)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        NoteEntity course = noteService.findById(id);
        if (course == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Note not found", null));
        }
        if (course.getDeletedAt() == null) {
            course.setDeletedAt(LocalDateTime.now());
        } else {
            course.setDeletedAt(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Note deleted successfully", noteService.save(course)));
    }
}

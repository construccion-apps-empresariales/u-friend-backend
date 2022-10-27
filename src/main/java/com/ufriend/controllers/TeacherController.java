package com.ufriend.controllers;

import com.ufriend.dto.auth.UpdatePasswordDTO;
import com.ufriend.dto.http.ResponseDTO;
import com.ufriend.language.LanguageService;
import com.ufriend.teacher.TeacherEntity;
import com.ufriend.teacher.TeacherService;
import com.ufriend.theme.ThemeEntity;
import com.ufriend.theme.ThemeService;
import com.ufriend.user.UserEntity;
import com.ufriend.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController()
@RequestMapping("/teachers")
public class TeacherController extends ExceptionHandlerController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private LanguageService languageService;

    @Resource
    private ThemeService themeService;

    @GetMapping
    public ResponseEntity<ResponseDTO> index(){
        List<TeacherEntity> teachers = this.teacherService.list();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Teachers list", teachers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable Long id){
        TeacherEntity teacher = teacherService.findById(id);
        if (teacher == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Teacher not found", null));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, teacher));
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseDTO> getById(@RequestParam("email") String email){
        TeacherEntity teacher = teacherService.findByEmail(email);
        if (teacher == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Teacher not found", null));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, teacher));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody TeacherEntity body) {
        TeacherEntity teacher = this.teacherService.findByEmail(body.getEmail());
        if (teacher != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(false, "Teacher already exists", teacher));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Teacher created", this.teacherService.save(body)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody TeacherEntity body) {
        TeacherEntity dbTeacher = this.teacherService.findById(body.getId());
        if (dbTeacher == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Teacher not found", null));
        }
        TeacherEntity alreadyExists = this.teacherService.findByEmail(body.getEmail());
        if (alreadyExists != null ) {
            if (!Objects.equals(alreadyExists.getId(), dbTeacher.getId()))
                return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(false, "There is another teacher with the email you submitted", dbTeacher));
        }
        dbTeacher.setName(body.getName());
        dbTeacher.setLastname(body.getLastname());
        dbTeacher.setEmail(body.getEmail());
        if (body.getPhone() != null)
            if (!body.getPhone().equals(""))
                dbTeacher.setPhone(body.getPhone());
        dbTeacher.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Teacher updated", this.teacherService.save(dbTeacher)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        TeacherEntity teacher = teacherService.findById(id);
        if (teacher == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Teacher not found", null));
        }
        if (teacher.getDeletedAt() == null) {
            teacher.setDeletedAt(LocalDateTime.now());
        } else {
            teacher.setDeletedAt(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Teacher deleted successfully", teacherService.save(teacher)));
    }
}

package com.ufriend.controllers;

import com.ufriend.course.CourseEntity;
import com.ufriend.course.CourseService;
import com.ufriend.dto.http.ResponseDTO;
import com.ufriend.teacher.TeacherService;
import com.ufriend.theme.ThemeService;
import com.ufriend.user.UserEntity;
import com.ufriend.user.UserService;
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
@RequestMapping("/courses")
public class CourseController extends ExceptionHandlerController {

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;

    @Resource
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ResponseDTO> index(){
        List<CourseEntity> courses = this.courseService.list();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Courses list", courses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable Long id){
        CourseEntity course = courseService.findById(id);
        if (course == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Course not found", null));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, null, course));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestParam String email, @Valid @RequestBody CourseEntity body) {
        UserEntity dbUser = this.userService.findByEmail(email);
        if (dbUser == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "User not found", null));
        }
        body.setUser(dbUser);
        CourseEntity savedCourse = this.courseService.save(body);
        if (savedCourse.getTeacher() != null) {
            savedCourse.setTeacher(teacherService.findById(savedCourse.getTeacher().getId()));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Course created", savedCourse));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody CourseEntity body) {
        CourseEntity dbCourse = this.courseService.findById(body.getId());
        if (dbCourse == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Course not found", null));
        }

        if (body.getName() != null)
            if (!body.getName().equals(""))
                dbCourse.setName(body.getName());

        dbCourse.setApproveNote(body.getApproveNote());
        dbCourse.setMinNote(body.getMinNote());
        dbCourse.setMaxNote(body.getMaxNote());

        if (body.getTeacher() != null)
            if (body.getTeacher().getId() != null)
                dbCourse.setTeacher(this.teacherService.findById(body.getTeacher().getId()));

        if (body.getStarts() != null)
            dbCourse.setStarts(body.getStarts());

        if (body.getEnds() != null)
            dbCourse.setEnds(body.getEnds());

        dbCourse.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Course updated", this.courseService.save(dbCourse)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        CourseEntity course = courseService.findById(id);
        if (course == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Course not found", null));
        }
        if (course.getDeletedAt() == null) {
            course.setDeletedAt(LocalDateTime.now());
        } else {
            course.setDeletedAt(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Course deleted successfully", courseService.save(course)));
    }
}

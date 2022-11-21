package com.ufriend.controllers;

import com.ufriend.course.CourseEntity;
import com.ufriend.course.CourseService;
import com.ufriend.dto.auth.UpdatePasswordDTO;
import com.ufriend.dto.http.ResponseDTO;

import com.ufriend.language.LanguageService;
import com.ufriend.theme.ThemeService;
import com.ufriend.user.UserEntity;
import com.ufriend.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/user")
public class UserInfoController extends ExceptionHandlerController {

    @Resource
    private UserService userService;

    @Resource
    private LanguageService languageService;

    @Resource
    private ThemeService themeService;

    @Resource
    private CourseService courseService;

    @GetMapping("/info")
    public ResponseEntity<ResponseDTO> getInfo(@RequestParam String email) {
        UserEntity dbUser = this.userService.findByEmail(email);
        if (dbUser == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "User not found", null));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "User information", dbUser));
    }

    @PutMapping("/update_info")
    public ResponseEntity<ResponseDTO> updateInfo(@RequestBody UserEntity body){
        UserEntity user = this.userService.findById(body.getId());
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "User not found", null));
        }
        if (body.getName() != null){
            if (!body.getName().equals(""))
                user.setName(body.getName());
        }
        if (body.getLastname() != null){
            if (!body.getLastname().equals(""))
                user.setLastname(body.getLastname());
        }
        if (body.getEmail() != null){
            if (!body.getEmail().equals(""))
                user.setEmail(body.getEmail());
        }
        if (body.getPhone() != null){
            if (!body.getPhone().equals("")) {
                user.setPhone(body.getPhone());
            }
        }
        if (body.getTheme() != null){
            if (!body.getTheme().getId().equals("")) {
                user.setTheme(this.themeService.findById(body.getTheme().getId()));
            }
        }
        if (body.getLanguage() != null){
            if (!body.getLanguage().getId().equals("")) {
                user.setLanguage(this.languageService.findById(body.getLanguage().getId()));
            }
        }
        user.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "User information updated successfully", this.userService.save(user)));
    }

    @PutMapping("/change_password")
    public ResponseEntity<ResponseDTO> updatePassword(@RequestBody UpdatePasswordDTO body){
        UserEntity dbUser = this.userService.findById(body.getId());
        if (dbUser == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "User not found", null));
        }
        if (!dbUser.samePwd(body.getActualPassword())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, "Incorrect password", null));
        }
        if (!body.getNewPassword().equals(body.getConfirmPassword())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, "Password does not match", null));
        }
        dbUser.setPassword(body.getNewPassword());
        dbUser.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Password updated successfully", this.userService.save(dbUser)));
    }

    @PutMapping("/system_defaults")
    public ResponseEntity<ResponseDTO> restoreDefaults(@RequestBody UserEntity user) {
        UserEntity dbUser = this.userService.findById(user.getId());
        if (dbUser == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "User not found", null));
        }
        dbUser.setLanguage(languageService.findById("EN"));
        dbUser.setTheme(themeService.findById("01"));
        dbUser.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Default system configuration updated successfully", this.userService.save(dbUser)));
    }

    @PutMapping("/photo")
    public ResponseEntity<ResponseDTO> setPhoto(@RequestParam("image") MultipartFile image,
                                                @RequestParam("id") Long id){
        UserEntity dbUser = this.userService.findById(id);
        if (dbUser == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "User not found", null));
        }
        if (image.getSize() > 5242880) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, "File is too large, please reduce it's size or select a different one, max file size is 5MB!", null));
        }
        try {
            dbUser.setPhoto(Base64.getEncoder().encodeToString(image.getBytes()));
            dbUser.setMime(image.getContentType());
        } catch (IOException e) {
            dbUser.setPhoto(null);
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.I_AM_A_TEAPOT)
                    .body(new ResponseDTO(false, "We do not know what happened (╯°□°）╯︵ ┻━┻", null));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "Photo updated successfully", this.userService.save(dbUser)));
    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseDTO> getUserCourses(@RequestParam String email) {
        UserEntity dbUser = this.userService.findByEmail(email);
        if (dbUser == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "User not found", null));
        }
        List<CourseEntity> userCourses = this.courseService.getUserCourses(dbUser.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(true, "User courses", userCourses));
    }
}

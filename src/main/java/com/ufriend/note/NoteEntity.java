package com.ufriend.note;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ufriend.course.CourseEntity;
import com.ufriend.user.UserEntity;
import lombok.Data;

@Entity
@Data
@Table(name = "notes")
public class NoteEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "percentage", nullable = false)
    @NotNull
    @NotBlank
    private float percentage;
    
    @Column(name = "value")
    @NotNull
    @NotBlank
    private float value;

    @Column(name = "starts", nullable = false)
    private Date starts;

    @Column(name = "ends", nullable = false)
    private Date ends;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "user_id", nullable = false)
    @NotNull
    @NotBlank
    @ManyToOne
    private UserEntity userId;

    @Column(name = "course_id", nullable = false)
    @NotNull
    @NotBlank
    @ManyToOne
    private CourseEntity courseId;

    @Column(name = "father_id")
    @OneToOne
    private NoteEntity fatherId;
}

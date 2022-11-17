package com.ufriend.course;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.ufriend.teacher.TeacherEntity;
import com.ufriend.user.UserEntity;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "courses")
public class CourseEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    @Length(min = 1, max = 200)
    @NotNull
    private String name;

    @Column(name = "approve_note", columnDefinition = "REAL DEFAULT 3.0")
    private float approveNote = 3;

    @Column(name = "min_note", columnDefinition = "REAL DEFAULT 0.0")
    private float minNote = 0;

    @Column(name = "max_note", columnDefinition = "REAL DEFAULT 5.0")
    private float maxNote = 5;

    @Column(name = "starts")
    private LocalDateTime starts;

    @Column(name = "ends")
    private LocalDateTime ends;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    private TeacherEntity teacher;

    @ManyToOne
    private UserEntity user;
}

package com.ufriend.course;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ufriend.teacher.TeacherEntity;
import lombok.Data;
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

    @Column(name = "approve_note", nullable = false, columnDefinition = "REAL DEFAULT 3.0")
    @NotNull
    private float approveNote;

    @Column(name = "min_note", nullable = false, columnDefinition = "REAL DEFAULT 0.0")
    @NotNull
    private float minNote;

    @Column(name = "max_note", nullable = false, columnDefinition = "REAL DEFAULT 5.0")
    @NotNull
    private float maxNote;

    @Column(name = "starts", nullable = false)
    private Date starts;

    @Column(name = "ends", nullable = false)
    private Date ends;

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @NotNull
    @ManyToOne
    private TeacherEntity teacherId;
}

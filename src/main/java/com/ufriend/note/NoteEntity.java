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
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "notes")
public class NoteEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @Length(min = 1, max = 100)
    @NotNull
    private String name;

    @Column(name = "percentage", nullable = false)
    @NotNull
    private float percentage;
    
    @Column(name = "value")
    @NotNull
    private float value;

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
    private UserEntity userId;

    @NotNull
    @ManyToOne
    private CourseEntity courseId;

    @ManyToOne
    private NoteEntity fatherId;
}

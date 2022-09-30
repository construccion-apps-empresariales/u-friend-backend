package com.ufriend.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "courses")
public class CourseEntity {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    @Size(min = 1, max = 200)
    @NotNull
    @NotBlank
    private String name;

    // TODO teaher

    @Column(name = "approve_note", nullable = false, columnDefinition = "REAL DEFAULT 3.0")
    @NotNull
    @NotBlank
    private float approveNote;

    @Column(name = "min_note", nullable = false, columnDefinition = "REAL DEFAULT 0.0")
    @NotNull
    @NotBlank
    private float minNote;

    @Column(name = "max_note", nullable = false, columnDefinition = "REAL DEFAULT 5.0")
    @NotNull
    @NotBlank
    private float maxNote;

    @Column(name = "starts", nullable = false)
    Date starts;

    @Column(name = "ends", nullable = false)
    Date ends;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    Date created_at;

    @Column(name = "updated_at")
    Date updated_at;

    @Column(name = "deleted_at")
    Date deleted_at;

}

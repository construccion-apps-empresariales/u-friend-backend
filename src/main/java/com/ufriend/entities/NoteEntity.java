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
@Table(name = "notes")
public class NoteEntity {
    
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
    Date starts;

    @Column(name = "ends", nullable = false)
    Date ends;

    // TODO user, course, father

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    Date created_at;

    @Column(name = "updated_at")
    Date updated_at;

    @Column(name = "deleted_at")
    Date deleted_at;

}

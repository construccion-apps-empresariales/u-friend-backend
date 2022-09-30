package com.ufriend.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "teachers")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "lastname", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    @NotNull
    @NotBlank
    private String lastname;

    @Column(name = "email", unique = true, nullable = false, length = 150)
    @Size(min = 1, max = 150)
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column(name = "phone", length = 50)
    @Size(min = 1, max = 150)
    @NotNull
    @NotBlank
    private String phone;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    Date created_at;

    @Column(name = "updated_at")
    Date updated_at;

    @Column(name = "deleted_at")
    Date deleted_at;

}

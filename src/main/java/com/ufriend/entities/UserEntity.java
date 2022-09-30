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
@Table(name = "users")
public class UserEntity {
    
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

    @Column(name = "photo")
    private String photo;

    @Column(name = "email", nullable = false, length = 150)
    @Size(min = 1, max = 150)
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 6, max = 255)
    @NotNull
    @NotBlank
    private String password;

    @Column(name = "phone", length = 50)
    @Size(min = 1, max = 150)
    @NotNull
    @NotBlank
    private String phone;

    @Column(name = "confirmated", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean confirmated;

    // TODO rol, language, theme

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    Date created_at;

    @Column(name = "updated_at")
    Date updated_at;

    @Column(name = "deleted_at")
    Date deleted_at;

}

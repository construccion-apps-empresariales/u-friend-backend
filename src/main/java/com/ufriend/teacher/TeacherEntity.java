package com.ufriend.teacher;

import java.io.Serializable;
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
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "teachers")
public class TeacherEntity implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @Length(min = 1, max = 100)
    @NotNull
    private String name;

    @Column(name = "lastname", length = 100)
    @Size(min = 1, max = 100)
    private String lastname;

    @Column(name = "email", unique = true, nullable = false, length = 150)
    @Length(min = 1, max = 150)
    @NotNull
    @Email
    private String email;

    @Column(name = "phone", length = 50)
    @Size(min = 1, max = 150)
    private String phone;

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

}

package com.ufriend.user;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ufriend.language.LanguageEntity;
import com.ufriend.role.RoleEntity;
import com.ufriend.theme.ThemeEntity;
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
    private String phone;

    @Column(name = "confirmed", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean confirmed;


    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "role_id", nullable = false, length = 2)
    @Size(max = 2, min = 2, message = "The id length must be equals to 2, indicating the role code.")
    @NotNull
    @NotBlank
    @OneToOne
    private RoleEntity role_id;

    @Column(name = "language_id", nullable = false, length = 2)
    @Size(max = 2, min = 2, message = "The id length must be equals to 2, indicating the language code.")
    @NotNull
    @NotBlank
    @OneToMany
    private List<LanguageEntity> languageId;

    @Column(name = "theme_id", nullable = false, length = 2)
    @Size(max = 2, min = 2, message = "The id length must be equals to 2, indicating the role code.")
    @NotNull
    @NotBlank
    @OneToMany
    private List<ThemeEntity> theme_id;
}

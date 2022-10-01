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
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    @Length(min = 1, max = 100)
    private String name;

    @Column(name = "lastname", length = 100)
    @Length(min = 1, max = 100)
    private String lastname;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email", nullable = false, length = 150)
    @Length(min = 1, max = 150)
    @NotNull
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @Length(min = 6, max = 255)
    @NotNull
    private String password;

    @Column(name = "phone", length = 50)
    @Length(min = 1, max = 150)
    private String phone;

    @Column(name = "confirmed", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean confirmed;

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @NotNull
    @ManyToOne
    private RoleEntity roleId;

    @NotNull
    @ManyToOne
    private LanguageEntity languageId;

    @NotNull
    @ManyToOne
    private ThemeEntity themeId;
}

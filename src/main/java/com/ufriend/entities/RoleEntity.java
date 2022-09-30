package com.ufriend.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class RoleEntity {
    
    @Id
    @Column(name = "id", nullable = false, length = 2)
    @Size(max = 2, min = 2, message = "The id length must be equals to 2, indicating the theme code.")
    private String id;

    @Column(name = "name", nullable = false, length = 20)
    @Size(min = 1, max = 20)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    Date created_at;

    @Column(name = "updated_at")
    Date updated_at;

    @Column(name = "deleted_at")
    Date deleted_at;

}

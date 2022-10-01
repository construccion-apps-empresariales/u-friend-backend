package com.ufriend.role;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity implements Serializable {
    
    @Id
    @Column(name = "id", nullable = false, length = 2)
    @Length(max = 2, min = 2, message = "The id length must be equals to 2, indicating the role code.")
    private String id;

    @Column(name = "name", nullable = false, length = 20)
    @Length(min = 1, max = 20)
    @NotNull
    private String name;

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

}

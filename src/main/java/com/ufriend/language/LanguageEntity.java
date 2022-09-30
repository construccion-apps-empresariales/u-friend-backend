package com.ufriend.language;

import java.io.Serializable;
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
@Table(name = "languages")
public class LanguageEntity implements Serializable {
    
    @Id
    @Column(name = "id", nullable = false, length = 2)
    @Size(max = 2, min = 2, message = "The id length must be equals to 2, indicating the language code.")
    @NotNull
    @NotBlank
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    @Size(min = 1, max = 50)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "dictionary", columnDefinition = "TEXT")
    @NotNull
    @NotBlank
    private String dictionary;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

}

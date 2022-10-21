package com.ufriend.theme;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "themes")
public class ThemeEntity implements Serializable {

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final String NOT_NULL_MESSAGE = "The color can't be null";

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final String COLOR_PATTERN = "^#(?:[0-9a-fA-F]{3}){1,2}$";

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final String PATTERN_MESSAGE = "The color must be hexadecimal";

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final String SIZE_MESSAGE = "The color length must be equals to 7. IE: #FFFFFF";

    @Id
    @Column(name = "id", nullable = false, length = 2)
    @Size(max = 2, min = 2, message = "The id length must be equals to 2, indicating the theme code.")
    @NotNull(message = "The id can't be null.")
    private String id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    @Size(min = 1, max = 50, message = "The name length must be between 1 and 50")
    @NotNull(message = "The name can't be null")
    private String name;

    @Column(name = "primary_color")
    @NotNull(message = NOT_NULL_MESSAGE)
    @Size(min = 7, max = 7, message = SIZE_MESSAGE)
    @Pattern(regexp = COLOR_PATTERN, message = PATTERN_MESSAGE)
    private String primary_color;

    @Column(name = "secondary_color")
    @NotNull(message = NOT_NULL_MESSAGE)
    @Size(min = 7, max = 7, message = SIZE_MESSAGE)
    @Pattern(regexp = COLOR_PATTERN, message = PATTERN_MESSAGE)
    private String secondary_color;

    @Column(name = "warning_color")
    @NotNull(message = NOT_NULL_MESSAGE)
    @Size(min = 7, max = 7, message = SIZE_MESSAGE)
    @Pattern(regexp = COLOR_PATTERN, message = PATTERN_MESSAGE)
    private String warning_color;

    @Column(name = "danger_color")
    @NotNull(message = NOT_NULL_MESSAGE)
    @Size(min = 7, max = 7, message = SIZE_MESSAGE)
    @Pattern(regexp = COLOR_PATTERN, message = PATTERN_MESSAGE)
    private String danger_color;

    @Column(name = "success_color")
    @NotNull(message = NOT_NULL_MESSAGE)
    @Size(min = 7, max = 7, message = SIZE_MESSAGE)
    @Pattern(regexp = COLOR_PATTERN, message = PATTERN_MESSAGE)
    private String success_color;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

}

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
@Table(name = "sessions")
public class SessionEntity {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    @NotBlank
    private String toke;

    @Column(name = "refresh_token", nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    @NotBlank
    private String refreshToke;

    @Column(name = "is_logged_in", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    @NotNull
    @NotBlank
    private Boolean isLoggedIn;

    // TODO user

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    Date created_at;

    @Column(name = "updated_at")
    Date updated_at;

    @Column(name = "deleted_at")
    Date deleted_at;

}

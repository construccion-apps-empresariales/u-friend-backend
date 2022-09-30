package com.ufriend.session;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ufriend.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sessions")
public class SessionEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    @NotBlank
    private String token;

    @Column(name = "refresh_token", nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    @NotBlank
    private String refreshToken;

    @Column(name = "is_logged_in", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    @NotNull
    @NotBlank
    private Boolean isLoggedIn;

    @Column(name = "user_id", nullable = false)
    @NotNull
    @NotBlank
    @ManyToOne
    private UserEntity user;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}

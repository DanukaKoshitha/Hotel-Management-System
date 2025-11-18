package org.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "system_user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SystemUser {

    @Id
    @Column(name = "user_name",nullable = false , length = 80)
    private String userId;

    @Column(name = "keycloak_id" , nullable = false , length = 80)
    private String keycloakId;

    @Column(name = "first_name" , nullable = false , length = 100)
    private String firstName;

    @Column(name = "last_name" , nullable = false , length = 100)
    private String lastName;

    @Column(name = "email", nullable = false , length = 100, unique = true)
    private String email;

    @Column(name = "contact" , length = 20)
    private String contact;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(mappedBy = "systemUser" , cascade = CascadeType.ALL)
    private Otp otp;

}

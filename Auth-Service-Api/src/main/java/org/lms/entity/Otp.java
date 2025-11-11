package org.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Table(name = "otp")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Otp {

    @Id
    @Column(name = "property_id",nullable = false,length = 80)
    private String propertyId;

    @Column(name = "code",nullable = false,length = 80)
    private String code;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "attempts")
    private Integer attempts;

    @OneToOne
    @JoinColumn(name = "system_user_id",nullable = false,unique = true)
    private SystemUser systemUser;
}

package com.hotel_management_system.Hotel_Managment_Service_API.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
public class HotelEntity {
    @Id
    @Column(name = "hotel_id", length=80)
    private String hotelId;

    @Column(name = "hotel_name", nullable = false, length = 100)
    private String hotelName;

    @Column(name = "star_rating", nullable = false)
    private int starRating;

    @Column(nullable = false)
    @Lob
    private Blob description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "active_status")
    private boolean activeStatus;

    @Column(name = "starting_from")
    private BigDecimal startingFrom;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<BranchEntity> branches;
}

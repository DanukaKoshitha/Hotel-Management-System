package com.hotel_management_system.Hotel_Managment_Service_API.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class RoomEntity {

    @Id
    @Column(name = "room_id",length = 80, nullable = false)
    private String roomId;

    @Column(name = "room_number" , length = 80 , nullable = false)
    private String roomNumber;

    @Column(name = "room_type" , length = 80 , nullable = false)
    private String type;

    @Column(name = "bed_count")
    private int bedCount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_available")
    private boolean isAvailable;

    @ManyToOne()
    @JoinColumn(name="branch_id")
    private BranchEntity branch;

    @OneToMany(mappedBy = "room")
    private List<FacilityEntity> facilities;

    @OneToMany(mappedBy = "room")
    private List<RoomImageEntity>  roomImages;
}

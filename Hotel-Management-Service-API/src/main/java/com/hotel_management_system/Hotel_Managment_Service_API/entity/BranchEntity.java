package com.hotel_management_system.Hotel_Managment_Service_API.entity;

import com.hotel_management_system.Hotel_Managment_Service_API.util.BranchType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branch")
public class BranchEntity {
    @Id
    @Column(name = "branch_id" , length = 80)
    private String branchId;

    @Column(name = "branch_name" , nullable = false)
    private String branchName;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch_type" , nullable = false)
    private BranchType branchType;

    @Column(name = "room_count")
    private int roomCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    @OneToOne(mappedBy = "branch")
    private AddressEntity address;

    @OneToMany(mappedBy = "branch")
    private List<RoomEntity> room;
}

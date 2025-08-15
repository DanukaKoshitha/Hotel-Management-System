package com.hotel_management_system.Hotel_Managment_Service_API.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "facility")
public class FacilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , nullable = false , length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}

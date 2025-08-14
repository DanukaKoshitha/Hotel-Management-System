package com.hotel_management_system.Hotel_Managment_Service_API.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "room_image")
public class RoomImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FileFormatter fileFormatter;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}

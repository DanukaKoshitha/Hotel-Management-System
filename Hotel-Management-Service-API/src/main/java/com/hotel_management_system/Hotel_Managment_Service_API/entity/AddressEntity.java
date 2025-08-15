package com.hotel_management_system.Hotel_Managment_Service_API.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @Column(name = "address_id" , length = 80)
    private String addressId;

    @Column(name = "address_line" , nullable = false , length = 255)
    private String addressLine;

    @Column(name = "city" ,nullable = false , length = 100)
    private String city;

    @Column(name = "country" , nullable = false , length = 100)
    private String country;

    @Column(name = "latitude" , nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude" , nullable = false)
    private BigDecimal longitude;

    @OneToOne
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;
}

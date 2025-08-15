package com.hotel_management_system.Hotel_Managment_Service_API.repository;

import com.hotel_management_system.Hotel_Managment_Service_API.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,String> {
}

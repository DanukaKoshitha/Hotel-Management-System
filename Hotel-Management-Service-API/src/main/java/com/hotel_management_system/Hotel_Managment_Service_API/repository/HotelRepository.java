package com.hotel_management_system.Hotel_Managment_Service_API.repository;

import com.hotel_management_system.Hotel_Managment_Service_API.entity.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<HotelEntity, String> {

    @Query(value = "SELECT * FROM hotel WHERE hotel_name LIKE %?1% AND active_status=true", nativeQuery = true)
    Page<HotelEntity> searchHotels(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM hotel WHERE hotel_name LIKE %?1% AND active_status=true", nativeQuery = true)
    long countAllHotels(String searchText);
}

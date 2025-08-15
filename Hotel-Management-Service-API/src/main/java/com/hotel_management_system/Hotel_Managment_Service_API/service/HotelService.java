package com.hotel_management_system.Hotel_Managment_Service_API.service;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.HotelPaginateResponseDto;
import java.sql.SQLException;

public interface HotelService {
    public void create(RequestHotelDto dto);
    public void update(RequestHotelDto dto, String hotelId) throws SQLException;
    public void delete(String hotelId);
    public ResponseHotelDto findById(String hotelId) throws SQLException;
    public HotelPaginateResponseDto findAll(int page, int size, String searchText);
}

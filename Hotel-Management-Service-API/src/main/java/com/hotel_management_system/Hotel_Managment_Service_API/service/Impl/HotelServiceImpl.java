package com.hotel_management_system.Hotel_Managment_Service_API.service.Impl;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.HotelPaginateResponseDto;
import com.hotel_management_system.Hotel_Managment_Service_API.service.HotelService;
import java.sql.SQLException;

public class HotelServiceImpl implements HotelService {
    @Override
    public void create(RequestHotelDto dto) {

    }

    @Override
    public void update(RequestHotelDto dto, String hotelId) throws SQLException {

    }

    @Override
    public void delete(String hotelId) {

    }

    @Override
    public ResponseHotelDto findById(String hotelId) throws SQLException {
        return null;
    }

    @Override
    public HotelPaginateResponseDto findAll(int page, int size, String searchText) {
        return null;
    }
}

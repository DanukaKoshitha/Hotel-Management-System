package com.hotel_management_system.Hotel_Managment_Service_API.service.Impl;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestRoomDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseRoomDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.RoomPaginateResponseDto;
import com.hotel_management_system.Hotel_Managment_Service_API.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    @Override
    public void create(RequestRoomDto dto) {

    }

    @Override
    public void update(RequestRoomDto dto, String roomId) {

    }

    @Override
    public void delete(String roomId) {

    }

    @Override
    public ResponseRoomDto findById(String roomId) {
        return null;
    }

    @Override
    public RoomPaginateResponseDto findAll(int page, int size) {
        return null;
    }
}

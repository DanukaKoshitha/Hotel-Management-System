package com.hotel_management_system.Hotel_Managment_Service_API.service;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestRoomDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseRoomDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.RoomPaginateResponseDto;

public interface RoomService {
    public void create(RequestRoomDto dto);
    public void update(RequestRoomDto dto, String roomId);
    public void delete(String roomId);
    public ResponseRoomDto findById(String roomId);
    public RoomPaginateResponseDto findAll(int page, int size);
}

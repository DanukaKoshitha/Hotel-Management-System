package com.hotel_management_system.Hotel_Managment_Service_API.service;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestRoomImageDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseRoomImageDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.RoomImagePaginateResponseDto;

public interface RoomImageService {
    public void create(RequestRoomImageDto dto);
    public void update(RequestRoomImageDto dto, String imageId);
    public void delete(String imageId);
    public ResponseRoomImageDto findById(String imageId);
    public RoomImagePaginateResponseDto findAll(int page, int size, String roomId);
}

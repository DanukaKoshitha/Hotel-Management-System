package com.hotel_management_system.Hotel_Managment_Service_API.service.Impl;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestRoomImageDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseRoomImageDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.RoomImagePaginateResponseDto;
import com.hotel_management_system.Hotel_Managment_Service_API.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomImageServiceImpl implements RoomImageService {
    @Override
    public void create(RequestRoomImageDto dto) {

    }

    @Override
    public void update(RequestRoomImageDto dto, String imageId) {

    }

    @Override
    public void delete(String imageId) {

    }

    @Override
    public ResponseRoomImageDto findById(String imageId) {
        return null;
    }

    @Override
    public RoomImagePaginateResponseDto findAll(int page, int size, String roomId) {
        return null;
    }
}

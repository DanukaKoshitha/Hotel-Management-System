package com.hotel_management_system.Hotel_Managment_Service_API.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class RequestRoomImageDto {
    private MultipartFile file;
    private String roomId;
}

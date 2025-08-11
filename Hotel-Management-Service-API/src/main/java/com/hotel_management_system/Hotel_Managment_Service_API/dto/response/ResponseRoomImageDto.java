package com.hotel_management_system.Hotel_Managment_Service_API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoomImageDto {
    private Long id;
    private String directory;
    private String fileName;
    private String hash;
    private String resourceUrl;
    private String roomId;
}

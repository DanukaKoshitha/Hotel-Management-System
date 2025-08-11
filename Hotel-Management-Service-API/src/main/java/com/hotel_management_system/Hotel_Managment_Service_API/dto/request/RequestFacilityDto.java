package com.hotel_management_system.Hotel_Managment_Service_API.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestFacilityDto {
    private String name;
    private String roomId;
}

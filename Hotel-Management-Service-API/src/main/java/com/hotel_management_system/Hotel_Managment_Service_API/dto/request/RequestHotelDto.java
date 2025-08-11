package com.hotel_management_system.Hotel_Managment_Service_API.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHotelDto {
    private String description;
    private String hotelName;
    private int starRating;
    private BigDecimal startingFrom;
}

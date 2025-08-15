package com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseHotelDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelPaginateResponseDto {
    private List<ResponseHotelDto> dataList;
    private long dataCount;
}

package com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseRoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomPaginateResponseDto {
    private List<ResponseRoomDto> dataList;
    private long dataCount;
}

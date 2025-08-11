package com.hotel_management_system.Hotel_Managment_Service_API.dto.response;

import com.hotel_management_system.Hotel_Managment_Service_API.util.BranchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBranchDto {
    private String branchId;
    private String branchName;
    private BranchType branchType;
    private int roomCount;
    private String hotelId;
}

package com.hotel_management_system.Hotel_Managment_Service_API.service;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestFacilityDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseFacilityDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.FacilityPaginateResponseDto;

public interface FacilityService {
    public void create(RequestFacilityDto dto);
    public void update(RequestFacilityDto dto, String facilityId);
    public void delete(String facilityId);
    public ResponseFacilityDto findById(String facilityId);
    public FacilityPaginateResponseDto findAll(int page, int size, String roomId);
}

package com.hotel_management_system.Hotel_Managment_Service_API.service.Impl;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestFacilityDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseFacilityDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.FacilityPaginateResponseDto;
import com.hotel_management_system.Hotel_Managment_Service_API.service.FacilityService;

public class FacilityServiceImpl implements FacilityService {
    @Override
    public void create(RequestFacilityDto dto) {

    }

    @Override
    public void update(RequestFacilityDto dto, String facilityId) {

    }

    @Override
    public void delete(String facilityId) {

    }

    @Override
    public ResponseFacilityDto findById(String facilityId) {
        return null;
    }

    @Override
    public FacilityPaginateResponseDto findAll(int page, int size, String roomId) {
        return null;
    }
}

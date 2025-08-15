package com.hotel_management_system.Hotel_Managment_Service_API.service.Impl;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestBranchDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseBranchDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.BranchPaginateResponseDto;
import com.hotel_management_system.Hotel_Managment_Service_API.service.BranchService;

public class BranchServiceImpl implements BranchService {
    @Override
    public void create(RequestBranchDto dto) {

    }

    @Override
    public void update(RequestBranchDto dto, String branchId) {

    }

    @Override
    public void delete(String branchId) {

    }

    @Override
    public ResponseBranchDto findById(String branchId) {
        return null;
    }

    @Override
    public BranchPaginateResponseDto findAll(int page, int size, String searchText) {
        return null;
    }

    @Override
    public BranchPaginateResponseDto findByHotelId(int page, int size, String hotelId, String searchText) {
        return null;
    }
}

package com.hotel_management_system.Hotel_Managment_Service_API.service;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestBranchDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseBranchDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.BranchPaginateResponseDto;

public interface BranchService {
    public void create(RequestBranchDto dto);
    public void update(RequestBranchDto dto, String branchId);
    public void delete(String branchId);
    public ResponseBranchDto findById(String branchId);
    public BranchPaginateResponseDto findAll(int page , int size , String searchText);
    public BranchPaginateResponseDto findByHotelId(int page , int size , String hotelId , String searchText);
}

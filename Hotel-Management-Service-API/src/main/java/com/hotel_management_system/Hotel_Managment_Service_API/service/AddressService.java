package com.hotel_management_system.Hotel_Managment_Service_API.service;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestAddressDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseAddressDto;

public interface AddressService {
    public void create(RequestAddressDto requestAddressDto);
    public void update(RequestAddressDto requestAddressDto , String addressId);
    public void delete(String addressId);
    public ResponseAddressDto findById(String addressId);
    public ResponseAddressDto findByBranchId(String branchId);
}

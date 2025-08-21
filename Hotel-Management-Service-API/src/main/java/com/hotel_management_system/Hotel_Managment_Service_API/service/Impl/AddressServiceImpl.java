package com.hotel_management_system.Hotel_Managment_Service_API.service.Impl;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestAddressDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseAddressDto;
import com.hotel_management_system.Hotel_Managment_Service_API.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Override
    public void create(RequestAddressDto requestAddressDto) {

    }

    @Override
    public void update(RequestAddressDto requestAddressDto, String addressId) {

    }

    @Override
    public void delete(String addressId) {

    }

    @Override
    public ResponseAddressDto findById(String addressId) {
        return null;
    }

    @Override
    public ResponseAddressDto findByBranchId(String branchId) {
        return null;
    }
}

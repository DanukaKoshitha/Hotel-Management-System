package com.hotel_management_system.Hotel_Managment_Service_API.controller;

import com.hotel_management_system.Hotel_Managment_Service_API.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
}

package com.hotel_management_system.Hotel_Managment_Service_API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class testcontroller {
    @GetMapping("/check")
    public String Test(){
        return "Connected!.";
    }
}

package com.hotel_management_system.Hotel_Managment_Service_API.controller;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.service.HotelService;
import com.hotel_management_system.Hotel_Managment_Service_API.util.StanderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/hotel_management/api/v1/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/user/create")
    public ResponseEntity<StanderResponseDto> createHotelByUser(@RequestBody RequestHotelDto hotelRequestDto) {
        hotelService.create(hotelRequestDto);
        return new ResponseEntity<>(new StanderResponseDto(201, "Hotel created successfully", null),
                HttpStatus.CREATED);
    }

    @PutMapping("/admin/update/{hotelId}")
    public ResponseEntity<StanderResponseDto> updateHotel(@RequestBody RequestHotelDto hotelRequestDto, @PathVariable String hotelId) throws SQLException {
        hotelService.update(hotelRequestDto , hotelId);
        return new ResponseEntity<>(new StanderResponseDto(201, "Hotel updated successfully", null),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/host/delete/{hotelId}")
    public ResponseEntity<StanderResponseDto> deleteHotel(@PathVariable String hotelId) throws SQLException {
        hotelService.delete(hotelId);
        return new ResponseEntity<>(new StanderResponseDto(204, "Hotel deleted successfully", null),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/visitor/find-by-id/{hotelId}")
    public ResponseEntity<StanderResponseDto> findHotelById(@PathVariable String hotelId) throws SQLException {
        ResponseHotelDto hotel = hotelService.findById(hotelId);
        return new ResponseEntity<>(new StanderResponseDto(200, "Hotel found successfully", hotel), HttpStatus.OK);
    }

    @GetMapping("/visitor/find-all")
    public ResponseEntity<StanderResponseDto> findAllHotels(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String searchText
    ) throws SQLException {
        return new ResponseEntity<>(new StanderResponseDto(200, "Hotels found successfully", hotelService.findAll(page, size, searchText)), HttpStatus.OK);
    }
}

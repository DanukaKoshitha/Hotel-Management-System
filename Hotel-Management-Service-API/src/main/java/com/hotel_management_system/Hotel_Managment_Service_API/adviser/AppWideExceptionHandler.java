package com.hotel_management_system.Hotel_Managment_Service_API.adviser;

import com.hotel_management_system.Hotel_Managment_Service_API.exception.EntryNotFoundException;
import com.hotel_management_system.Hotel_Managment_Service_API.util.StanderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    public ResponseEntity<StanderResponseDto> handleEntryNotFoundException(EntryNotFoundException ex){
        return new ResponseEntity<>(
                new StanderResponseDto(
                        404,
                        ex.getMessage(),
                        ex
                ), HttpStatus.NOT_FOUND
        );
    }
}

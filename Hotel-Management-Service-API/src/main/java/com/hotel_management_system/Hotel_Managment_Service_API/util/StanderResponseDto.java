package com.hotel_management_system.Hotel_Managment_Service_API.util;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StanderResponseDto {
    private int code;
    private String message;
    private Object data;
}

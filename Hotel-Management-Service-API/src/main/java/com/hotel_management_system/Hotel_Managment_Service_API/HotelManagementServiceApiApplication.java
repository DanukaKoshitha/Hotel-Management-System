package com.hotel_management_system.Hotel_Managment_Service_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelManagementServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementServiceApiApplication.class, args);
	}

}

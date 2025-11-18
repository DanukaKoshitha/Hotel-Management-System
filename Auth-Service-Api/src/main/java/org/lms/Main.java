package org.lms;

import lombok.RequiredArgsConstructor;
import org.lms.dto.request.SystemUserRequestDto;
import org.lms.service.SystemUserService;
import org.lms.util.PasswordGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    private final SystemUserService service;
    private final PasswordGenerator generator;

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Override
    public void run(String... args) throws Exception {

        SystemUserRequestDto user1= new SystemUserRequestDto("ABC","XYZ","abc@gmail.com",generator.generatePassword(),"0714911257");
        SystemUserRequestDto user2= new SystemUserRequestDto("STY","WTY","sty@gmail.com",generator.generatePassword(),"0714875865");

        service.initializeHosts(Arrays.asList(user1, user2));    }
}
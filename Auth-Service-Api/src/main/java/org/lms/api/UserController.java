package org.lms.api;

import lombok.RequiredArgsConstructor;
import org.lms.config.JwtService;
import org.lms.dto.request.LoginRequestDto;
import org.lms.dto.request.PasswordRequestDto;
import org.lms.dto.request.SystemUserRequestDto;
import org.lms.service.SystemUserService;
import org.lms.util.StanderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service/api/v1/users")
public class UserController {
    private final SystemUserService systemUserService;
    private final JwtService jwtService;

    @PostMapping("/visitors/signup")
    public ResponseEntity<StanderResponseDto> createUser(@RequestBody SystemUserRequestDto dto) throws IOException {
        systemUserService.createUser(dto);
        return new ResponseEntity<>(
                new StanderResponseDto(201,"User Account was created",null),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/visitors/resend")
    public ResponseEntity<StanderResponseDto> resend(@RequestParam String email, @RequestParam String type){
        systemUserService.resend(email,type);
        return new ResponseEntity<>(
                new StanderResponseDto(200,"Please check your email",null),
                HttpStatus.OK
        );
    }

    @PostMapping("/visitors/forgot-password-request-code")
    public ResponseEntity<StanderResponseDto> forgotPasswordRequest(@RequestParam String email){
        systemUserService.forgotPasswordSendVerificationCode(email);
        return new ResponseEntity<>(
                new StanderResponseDto(200,"Please check your email",null),
                HttpStatus.OK
        );
    }

    @PostMapping("/visitors/verify-reset")
    public ResponseEntity<StanderResponseDto> verifyReset(@RequestParam String email, @RequestParam String otp){
        boolean isVerify = systemUserService.verifyReset(otp, email);
        return new ResponseEntity<>(
                new StanderResponseDto(isVerify?200:400,isVerify?"Please check your email":"try again",isVerify),
                isVerify?HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/visitors/reset-password")
    public ResponseEntity<StanderResponseDto> resetPassword(@RequestBody PasswordRequestDto dto) throws IOException{
        boolean isChanged = systemUserService.passwordReset(dto);
        return new ResponseEntity<>(
                new StanderResponseDto(isChanged?201:400,isChanged?"CHANGED":"try Again",isChanged),
                isChanged?HttpStatus.CREATED:HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/visitors/verify-email")
    public ResponseEntity<StanderResponseDto> verifyEmail(@RequestParam String email,@RequestParam String otp) throws IOException{
        boolean isVerified = systemUserService.verifyEmail(otp,email);
        return new ResponseEntity<>(
                new StanderResponseDto(isVerified?200:400,isVerified?"Verified":"try Again",isVerified),
                isVerified?HttpStatus.OK:HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/visitors/login")
    public ResponseEntity<StanderResponseDto> login(@RequestBody LoginRequestDto dto) throws IOException{
        return new ResponseEntity<>(
                new StanderResponseDto(200,"success",systemUserService.userLogin(dto)),
                HttpStatus.OK
        );
    }
}

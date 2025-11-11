package org.lms.adviser;

import org.lms.exception.BadRequestException;
import org.lms.util.StanderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    public ResponseEntity<StanderResponseDto> handleBadRequestException(BadRequestException ex){
        return new ResponseEntity<>(
                new StanderResponseDto(
                        400,
                        ex.getMessage(),
                        ex
                ), HttpStatus.BAD_REQUEST
        );
    }
}

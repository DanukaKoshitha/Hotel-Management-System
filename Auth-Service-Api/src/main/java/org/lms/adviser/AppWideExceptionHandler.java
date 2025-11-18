package org.lms.adviser;

import org.lms.exception.BadRequestException;
import org.lms.exception.EntryNotFoundException;
import org.lms.util.StanderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StanderResponseDto> handleBadRequestException(BadRequestException ex){
        return new ResponseEntity<>(
                new StanderResponseDto(
                        400,
                        ex.getMessage(),
                        ex
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StanderResponseDto> handleEntryNotFoundException(EntryNotFoundException ex) {
        return new ResponseEntity<StanderResponseDto>(
                new StanderResponseDto(404,ex.getMessage(),ex),
                HttpStatus.NOT_FOUND
        );
    }
}

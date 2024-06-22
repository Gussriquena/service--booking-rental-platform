package com.service.booking.rental.platform.configs;

import com.service.booking.rental.platform.entities.GeneralError;
import com.service.booking.rental.platform.exceptions.BookingNotFoundException;
import com.service.booking.rental.platform.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerAdviceConfiguration {


    @ResponseBody
    @ExceptionHandler({ BookingNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public GeneralError notFoundException(Exception e){
        return GeneralError.builder()
                .code(404)
                .message(e.getMessage())
                .build();
    }


}

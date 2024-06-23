package com.service.booking.rental.platform.configs;

import com.service.booking.rental.platform.entities.GeneralError;
import com.service.booking.rental.platform.exceptions.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerAdviceConfiguration {


    @ResponseBody
    @ExceptionHandler({
            BookingNotFoundException.class,
            UserNotFoundException.class,
            PropertyNotFoundException.class
    })
    @ResponseStatus(NOT_FOUND)
    public GeneralError notFoundException(Exception e){
        return GeneralError.builder()
                .code(404)
                .message(e.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler({
            IllegalArgumentException.class,
            NullPointerException.class
    })
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public GeneralError internalErrorException(Exception e){
        return GeneralError.builder()
                .code(500)
                .message(e.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(PropertyUnavailableException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public GeneralError propertyUnavailableException(Exception e){
        return GeneralError.builder()
                .code(409)
                .message(e.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler({ HttpMessageNotReadableException.class, InvalidDateException.class})
    @ResponseStatus(BAD_REQUEST)
    public GeneralError badRequestException(Exception e){
        return GeneralError.builder()
                .code(400)
                .message(e.getMessage())
                .build();
    }
}

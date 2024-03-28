package com.kstrinadka.managerproject.handlers;

import com.kstrinadka.managerproject.exceptions.NoHashException;
import com.kstrinadka.managerproject.exceptions.NoMaxLengthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientExceptionsHandler
{
    @ExceptionHandler(value = {NoHashException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String HashWasNotProvidedExceptionHandler(NoHashException exception)
    {
        return exception.getMessage();
    }

    @ExceptionHandler(value = {NoMaxLengthException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String MaxLengthWasNotProvided(NoMaxLengthException exception)
    {
        return exception.getMessage();
    }
}

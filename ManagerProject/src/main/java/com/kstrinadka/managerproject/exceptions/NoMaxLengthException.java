package com.kstrinadka.managerproject.exceptions;

public class NoMaxLengthException extends RuntimeException
{
    public NoMaxLengthException()
    {
        super("Request does not contain maxLength or maxLength = 0");
    }
}

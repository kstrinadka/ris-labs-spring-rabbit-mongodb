package com.kstrinadka.managerproject.exceptions;

public class NoHashException extends RuntimeException
{
    public NoHashException()
    {
        super("Request does not contain hash!");
    }
}

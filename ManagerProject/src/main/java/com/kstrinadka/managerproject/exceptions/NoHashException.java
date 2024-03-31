package com.kstrinadka.managerproject.exceptions;

public class NoHashException extends RuntimeException
{
    // todo - добавить возможность месседжа (объект хотя бы передавать сюда)
    public NoHashException()
    {
        super("Request does not contain hash!");
    }
}

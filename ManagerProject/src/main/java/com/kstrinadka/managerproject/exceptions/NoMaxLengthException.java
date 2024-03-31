package com.kstrinadka.managerproject.exceptions;

public class NoMaxLengthException extends RuntimeException
{
    // todo - добавить возможность месседжа (объект хотя бы передавать сюда)
    public NoMaxLengthException()
    {
        super("Request does not contain maxLength or maxLength = 0");
    }
}

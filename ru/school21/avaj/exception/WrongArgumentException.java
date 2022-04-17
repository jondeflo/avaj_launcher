package ru.school21.avaj.exception;

public class WrongArgumentException extends RuntimeException{

    public WrongArgumentException(String msg)
    {
        super(msg);
    }
}

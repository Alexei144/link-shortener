package ru.fazlyev.linkshortener.exception;

public class NotFoundPageException extends LinkShortenerException{
    public NotFoundPageException(String message) {
        super(message);
    }
}

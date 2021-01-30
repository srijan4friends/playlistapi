package com.musicshare.playlistapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NameRequiredException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "playlist name is mandatory")
    public void handleNameRequiredException() {
    }

    @ExceptionHandler(IsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "song doesn't exist")
    public void handleSongNotFoundException() {
    }

}



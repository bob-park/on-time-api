package com.malgn.ontimeapi.handler;

import static com.malgn.common.exception.StandardProblemDetail.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.malgn.ontimeapi.domain.attendance.exception.ExpiredException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ExpiredException.class,})
    public ProblemDetail expired(ExpiredException e) {
        log.error(e.getMessage());

        return createErrorDetail(e.getErrorCode(), e);
    }

}

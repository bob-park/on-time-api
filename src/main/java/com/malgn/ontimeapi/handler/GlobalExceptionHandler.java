package com.malgn.ontimeapi.handler;

import static com.malgn.common.model.ApiResult.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.malgn.common.exception.AlreadyExecuteException;
import com.malgn.common.exception.AlreadyExistException;
import com.malgn.common.exception.ServiceUnavailableException;
import com.malgn.common.exception.code.ErrorCode;
import com.malgn.common.model.ApiResult;
import com.malgn.ontimeapi.domain.attendance.exception.ExpiredException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ExpiredException.class, AlreadyExistException.class, AlreadyExecuteException.class})
    public <T> ApiResult<T> expired(Exception e) {
        log.error(e.getMessage());

        return error("EXPIRED", "Expired.", e);
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceUnavailableException.class)
    public <T> ApiResult<T> serviceUnavailable(ServiceUnavailableException e) {
        log.warn(e.getMessage());

        ErrorCode code = ErrorCode.SERVICE_UNAVAILABLE;

        return error(code.getCode(), code.getMessage(), e);
    }

}

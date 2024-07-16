package com.farm.collector.management.exception;

import com.farm.collector.commons.ApiResponse;
import com.farm.collector.commons.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestControllerAdvice
@SuppressWarnings("unused")
public class ManagementExceptionHandler {

    private static ErrorResponse getErrorResponse(InvalidFormatException ifx) {
        var errorDetails = String.format("Invalid enum value: '%s' for the field: '%s'. "
                        + "The value must be one of: %s.",
                ifx.getValue(), ifx.getPath().get(ifx.getPath().size() - 1).getFieldName(),
                Arrays.toString(ifx.getTargetType().getEnumConstants()));

        return new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.toString(),
                BAD_REQUEST.name(), Collections.singletonList(errorDetails));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = OK)
    ApiResponse<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return ApiResponse.error(new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.toString(),
                BAD_REQUEST.name(), errors));
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    @ResponseStatus(value = OK)
    ApiResponse<Void> handleResponseStatusException(ResponseStatusException ex) {
        return ApiResponse.error(new ErrorResponse(ex.getStatusCode().value(), ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ApiResponse<ErrorResponse> handleException(Exception e) {
        if (e.getCause() instanceof InvalidFormatException ifx
                && (ifx.getTargetType() != null && ifx.getTargetType().isEnum())) {
            ErrorResponse response = getErrorResponse(ifx);

            return ApiResponse.error(response);
        }
        ErrorResponse response = new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.toString(),
                BAD_REQUEST.name());
        return ApiResponse.error(response);
    }
}

package io.akshay.partyinvitation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Global Rest API exception handling for this application
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Responds to user with appropriate error message and status in case of any runtime exceptions
     *
     * @param exception {@link InvitationRuntimeException} to be reported
     * @param webRequest  Request for which error occurred
     * @return {@link ErrorResponse}
     */
    @ExceptionHandler(InvitationRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleRuntimeExceptions(Exception exception, WebRequest webRequest) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(exception.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return response;
    }
}

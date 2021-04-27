package io.akshay.partyinvitation.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    /**
     * Response Status
     */
    private int status;

    /**
     * Message shown to the user
     */
    private String errorMessage;


    /**
     * Occurrent time of the error
     */
    private LocalDateTime dateTime;

}

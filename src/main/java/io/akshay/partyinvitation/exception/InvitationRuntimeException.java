package io.akshay.partyinvitation.exception;


/**
 * Exception encompassing any nested exception
 */
public class InvitationRuntimeException extends Exception {

    public InvitationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

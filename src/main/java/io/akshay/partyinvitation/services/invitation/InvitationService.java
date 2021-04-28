package io.akshay.partyinvitation.services.invitation;

import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.models.Person;

import java.util.List;

/**
 * Get invitees for the party
 */
public interface InvitationService {

    /**
     * Get all invitees for an event
     * @return List of {@link Person}
     * @throws InvitationRuntimeException
     */
    List<? extends Person> getInvitees() throws InvitationRuntimeException;
}

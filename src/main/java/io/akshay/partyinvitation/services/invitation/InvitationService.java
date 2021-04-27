package io.akshay.partyinvitation.services.invitation;

import com.fasterxml.jackson.annotation.JsonView;
import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Person;

import java.util.List;

/**
 * Get invitees for the party
 */
public interface InvitationService {

    List<Person> getInvitees() throws InvitationRuntimeException;
}

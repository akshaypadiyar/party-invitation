package io.akshay.partyinvitation.api;

import com.fasterxml.jackson.annotation.JsonView;
import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Person;
import io.akshay.partyinvitation.serialization.views.View;
import io.akshay.partyinvitation.services.invitation.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * API for managing invitees to a party
 */
@RestController
@RequestMapping(value = "/invitees",
        name = "API for managing invitees to a party",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @JsonView(View.Summary.class)
    @GetMapping(name = "Get all invitees for the party")
    public List<Person> getInvitees() throws InvitationRuntimeException {
        return new ArrayList<>(invitationService.getInvitees());
    }
}

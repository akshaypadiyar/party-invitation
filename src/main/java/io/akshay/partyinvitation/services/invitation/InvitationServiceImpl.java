package io.akshay.partyinvitation.services.invitation;

import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Person;
import io.akshay.partyinvitation.providers.Filter;
import io.akshay.partyinvitation.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple implementation of {@link InvitationService}.
 */
@Slf4j
@Service
public final class InvitationServiceImpl implements InvitationService {

    private final CustomerRepository customerRepository;
    private final Filter<Customer> filterStrategy;
    private final Comparator<Customer> sortingStrategy;

    public InvitationServiceImpl(
            final CustomerRepository customerRepository,
            final Filter<Customer> filterStrategy,
            final Comparator<Customer> sortingStrategy) {
        this.customerRepository = customerRepository;
        this.filterStrategy = filterStrategy;
        this.sortingStrategy = sortingStrategy;
    }

    /**
     * Get invitees for the event
     * @return List of {@link Customer}
     */
    @Override
    public List<? extends Person> getInvitees() throws InvitationRuntimeException {
        log.trace("Retrieving all customers");

        List<Customer> customers = null;
        try {
            customers = customerRepository.load();
        } catch (Exception e) {
            throw new InvitationRuntimeException("Error loading customers from repository", e);
        }

        log.debug("Got {} customer(s)", customers.size());

        var eligibleCustomers = customers.stream()
                .filter(filterStrategy)
                .collect(Collectors.toList());
        log.info("Found {} eligible customers", eligibleCustomers.size());

        log.trace("Sorting customers");
        eligibleCustomers.sort(sortingStrategy);

        return new ArrayList<>(eligibleCustomers);
    }
}

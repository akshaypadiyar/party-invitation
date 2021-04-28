package io.akshay.partyinvitation.services.invitation;

import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Person;
import io.akshay.partyinvitation.providers.Filter;
import io.akshay.partyinvitation.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InvitationServiceImplTest {

    private InvitationService invitationService;

    private CustomerRepository customerRepository;
    private Filter<Customer> filterStrategy;
    private Comparator<Customer> sortingStrategy;

    private List<Customer> sampleCustomers;

    @BeforeEach
    void setUp() throws Exception {
        sampleCustomers = TestUtils.getSampleCustomers();

        customerRepository = mock(CustomerRepository.class);
        filterStrategy = mock(Filter.class);
        sortingStrategy = (c1, c2) -> Comparator.comparing(Customer::getName).compare(c1, c2);

        when(customerRepository.load()).thenReturn(sampleCustomers);

        invitationService = new InvitationServiceImpl(customerRepository, filterStrategy, sortingStrategy);
    }

    @Test
    void testLoad() throws Exception {

        invitationService.getInvitees();

        verify(customerRepository, times(1)).load();
        verify(filterStrategy, times(sampleCustomers.size())).test(any(Customer.class));
    }

    @Test
    void testExceptionWhenLoadFails() throws Exception {

        when(customerRepository.load()).thenThrow(InvitationRuntimeException.class);

        assertAll(
                () -> assertThrows(InvitationRuntimeException.class, () -> invitationService.getInvitees()),
                () -> verify(filterStrategy, never()).test(any(Customer.class))
        );
    }

    @Test
    void testWhenAllCustomersAreFilteredOut() throws Exception {

        when(filterStrategy.test(any(Customer.class))).thenReturn(false);

        List<? extends Person> invitees = invitationService.getInvitees();

        verify(customerRepository, times(1)).load();
        verify(filterStrategy, times(sampleCustomers.size())).test(any(Customer.class));

        assertEquals(0, invitees.size());


    }
}
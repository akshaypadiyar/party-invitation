package io.akshay.partyinvitation.repository;

import io.akshay.partyinvitation.InvitationApplication;
import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.io.FileReader;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.serialization.reader.Parser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = InvitationApplication.class)
public class CustomerLoadTests {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private Parser<Customer> parser;

    private FileRepository repository;


    @Test
    void testLoadingAllCustomers() throws InvitationRuntimeException {
        String dataSource = "data/all-customers.txt";
        repository = new FileRepository(fileReader, dataSource, parser);

        var customers = repository.load();

        assertEquals(32, customers.size());
    }

    @Test
    void testLoadingSomeInvalidCustomers() throws InvitationRuntimeException {
        String dataSource = "data/some-invalid-customers.txt";

        repository = new FileRepository(fileReader, dataSource, parser);

        var customers = repository.load();

        var expectedCustomerIds = Set.of(9, 13, 39, 28);
        var loadedCustomerIds = customers.stream().map(Customer::getId).collect(Collectors.toSet());

        assertEquals(4, customers.size());
        assertAll(
                () -> expectedCustomerIds.containsAll(loadedCustomerIds),
                () -> loadedCustomerIds.containsAll(expectedCustomerIds)
        );
    }

}

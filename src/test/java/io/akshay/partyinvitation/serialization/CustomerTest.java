package io.akshay.partyinvitation.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.exception.ParseException;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Location;
import io.akshay.partyinvitation.serialization.reader.CustomerParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CustomerTest {

    private CustomerParser parser;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws JsonProcessingException {
        Customer dummy = new Customer();
        dummy.setId(1);
        dummy.setName("Test");
        dummy.setLocation(new Location(1, -1));

        objectMapper = mock(ObjectMapper.class);
        when(objectMapper.readValue(anyString(), eq(Customer.class))).thenReturn(dummy);

        parser = new CustomerParser(objectMapper, false);
    }

    @Test
    public void readingValidCustomer() throws ParseException, IOException {
        String customerText = TestUtils.readFileContents("data/valid_customer.txt");
        Customer customer = parser.parse(customerText);
        verify(objectMapper, times(1)).readValue(customerText, Customer.class);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(customer),
                () -> Assertions.assertEquals("Test", customer.getName()),
                () -> Assertions.assertEquals(1, customer.getId()),
                () -> Assertions.assertNotNull(customer.getLocation()),
                () -> Assertions.assertEquals(1, customer.getLocation().getLatitude()),
                () -> Assertions.assertEquals(-1, customer.getLocation().getLongitude())
        );
    }

    @Test
    public void readThrowExceptionWhenObjectMapperFails() throws JsonProcessingException {
        String customerText = "Test";
        when(objectMapper.readValue(anyString(), eq(Customer.class))).thenThrow(JsonProcessingException.class);
        Assertions.assertThrows(ParseException.class, () -> parser.parse(customerText));
    }
}
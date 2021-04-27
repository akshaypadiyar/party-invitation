package io.akshay.partyinvitation.repository;

import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.exception.ParseException;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.serialization.reader.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FileRepositoryTest {

    private FileRepository repository;

    private FileReader fileReader;

    private Parser<Customer> parser;

    private String filePath;

    @BeforeEach
    void setUp() throws IOException, ParseException {

        parser = mock(Parser.class);
        fileReader = mock(FileReader.class);
        filePath = "data/valid_customer.txt";

        String customerText = "anyText";
        when(fileReader.readFile(any(URI.class))).thenReturn(List.of(customerText));

        Customer dummy = TestUtils.getDummyCustomer();

        when(parser.parse(eq(customerText))).thenReturn(dummy);

        repository = new FileRepository(fileReader, filePath, parser);
    }

    @Test
    void load() throws InvitationRuntimeException, IOException, ParseException {

        var customers = repository.load();

        verify(fileReader, times(1)).readFile(any(URI.class));
        verify(parser, times(1)).parse(anyString());

        assertEquals(1, customers.size());
    }

    @Test
    void testWhenFileReadThrowsException() throws IOException {
        when(fileReader.readFile(any(URI.class))).thenThrow(IOException.class);
        assertThrows(InvitationRuntimeException.class, () -> repository.load());
    }
}
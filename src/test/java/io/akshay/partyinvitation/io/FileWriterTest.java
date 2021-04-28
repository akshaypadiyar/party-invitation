package io.akshay.partyinvitation.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.serialization.views.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FileWriterTest {

    private FileWriter fileWriter;

    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;

    @TempDir
    File temporaryFolder;

    @BeforeEach
    void setup() throws JsonProcessingException {

        objectMapper = mock(ObjectMapper.class);
        objectWriter = mock(ObjectWriter.class);
        when(objectMapper.writerWithView(any(Class.class))).thenReturn(objectWriter);

        byte[] randomBytes = new byte[10];
        new Random().nextBytes(randomBytes);

        when(objectWriter.writeValueAsBytes(any())).thenReturn(randomBytes);
        fileWriter = new FileWriter(objectMapper);

        assertTrue(temporaryFolder.isDirectory());
    }

    @Test
    void testWriter() throws IOException {
        List<Customer> customers = TestUtils.getSampleCustomers();

        File output = new File(temporaryFolder, "output.txt");

        URI outputUri = output.toURI();
        fileWriter.writeFile(customers, outputUri);

        assertAll(
                () -> assertTrue(output.length() > 0),
                () -> verify(objectMapper, times(1)).writerWithView(eq(View.Summary.class)),
                () -> verify(objectWriter, times(1)).writeValueAsBytes(any()));
    }
}
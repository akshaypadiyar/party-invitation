package io.akshay.partyinvitation.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.akshay.partyinvitation.models.Person;
import io.akshay.partyinvitation.serialization.views.View;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class FileWriter {

    private final ObjectMapper objectMapper;

    public FileWriter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Write customers to given file path
     * @param customers List of {@link Person} to be written
     * @param fileUri {@link URI} where the contents to be written
     */
    public void writeFile(List<? extends Person> customers, URI fileUri) throws IOException {
        try {
            Path path = Path.of(fileUri);
            Files.write(path, getContents(customers));

        } catch (IOException e) {
            log.error("Error writing customers to output file. Cause: {}", e.toString());
            throw e;
        }
    }

    private byte[] getContents(List<? extends Person> customers) throws JsonProcessingException {
        return objectMapper.writerWithView(View.Summary.class).writeValueAsBytes(customers);
    }
}

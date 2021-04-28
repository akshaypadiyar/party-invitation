package io.akshay.partyinvitation.repository;

import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.io.FileReader;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.serialization.reader.Parser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * File-based repository for customer entity
 */
@Slf4j
@Service
public class FileRepository implements CustomerRepository {

    private final FileReader reader;
    private final Parser<Customer> parser;
    private final String dataSource;

    public FileRepository(@NonNull final FileReader reader,
                          @Value("${customers.data.source}") final String dataSource,
                          @NonNull final Parser<Customer> parser) {
        this.reader = reader;
        this.dataSource = dataSource;
        this.parser = parser;
    }

    /**
     * Loads the list of customers from file
     *
     * @return List of {@link Customer}
     * @throws InvitationRuntimeException
     */
    @Override
    public List<Customer> load() throws InvitationRuntimeException {
        try {
            return reader
                    .readFile(getDataSource())
                    .stream()
                    .map(this::parse)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error loading customers from file {}", dataSource);
            throw new InvitationRuntimeException("Error loading customers from repository", e);
        }
    }

    private URI getDataSource() throws IOException {
        return new ClassPathResource(dataSource).getURI();
    }

    @SneakyThrows
    private Customer parse(String text) {
        return parser.parse(text);
    }
}

package io.akshay.partyinvitation.repository;

import io.akshay.partyinvitation.exception.InvitationRuntimeException;
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
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * File-based repository for customer entity
 */
@Slf4j
@Service
public class FileRepository implements CustomerRepository {

    private FileReader reader;
    private String fileEncoding;
    private Parser<Customer> parser;

    private String filePath;

    public FileRepository(@NonNull final FileReader reader,
                          @Value("${customers.data.source}") final String dataSource,
                          @NonNull final Parser<Customer> parser) {
        this.reader = reader;
        this.filePath = dataSource;
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
                    .readFile(getFilePath())
                    .stream()
                    .map(this::parse)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while loading customers from file {}", filePath);
            throw new InvitationRuntimeException("Error while loading customers from repository", e);
        }
    }

    private URI getFilePath() throws IOException {
        return new ClassPathResource(this.filePath).getURI();
    }

    @SneakyThrows
    private Customer parse(String text) {
        return parser.parse(text);
    }
}

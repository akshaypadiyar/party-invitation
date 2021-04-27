package io.akshay.partyinvitation.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Reads given files and return the content
 */
@Slf4j
@Service
public class FileReader {

    /**
     * Reads the given file and returns the content
     *
     * @param fileUri {@link URI} of the file to read
     * @return Content as {@link List} of strings
     * @throws IOException
     */
    public List<String> readFile(URI fileUri) throws IOException {
        Path path = Path.of(fileUri);
        return Files.readAllLines(path);
    }
}

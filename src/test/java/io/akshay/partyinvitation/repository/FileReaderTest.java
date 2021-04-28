package io.akshay.partyinvitation.repository;

import io.akshay.partyinvitation.io.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    FileReader reader;

    @BeforeEach
    void setUp() {
        reader = new FileReader();
    }

    @Test
    void readValidFile() throws IOException {
        var expectedLines = List.of("Line 1", "Line 2", "Line 3");
        var lines = reader.readFile(getFileUri("data/valid_file.txt"));
        assertLinesMatch(expectedLines, lines);
    }

    @Test
    void readInvalidFile() {
        assertThrows(IOException.class, () -> reader.readFile(getFileUri("data/invalid_file.txt")));
    }

    private static URI getFileUri(String fileName) throws IOException {
        return new ClassPathResource(fileName).getURI();
    }
}
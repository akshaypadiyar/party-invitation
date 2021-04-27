package io.akshay.partyinvitation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.models.Person;
import io.akshay.partyinvitation.serialization.views.View;
import io.akshay.partyinvitation.services.invitation.InvitationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@SpringBootApplication
public class ConsoleApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(ConsoleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        InvitationService invitationService = applicationContext.getBean(InvitationService.class);
        ObjectMapper objectMapper = applicationContext.getBean(ObjectMapper.class);

        try {
            var outputPath = getOutputPath();
            var customers = invitationService.getInvitees();

            Files.write(outputPath, getContents(objectMapper, customers));

        } catch (InvitationRuntimeException e) {
            log.error("Error getting customers. Cause: {}", e.toString());
        } catch (IOException e) {
            log.error("Error writing customers to output file. Cause: {}", e.toString());
        }
    }

    private static byte[] getContents(ObjectMapper mapper, List<Person> customers) throws JsonProcessingException {
        return mapper.writerWithView(View.Summary.class).writeValueAsBytes(customers);
    }

    private static Path getOutputPath() {
        return Path.of(System.getProperty("user.dir"), "output.json");
    }
}

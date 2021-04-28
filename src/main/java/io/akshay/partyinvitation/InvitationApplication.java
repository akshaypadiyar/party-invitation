package io.akshay.partyinvitation;

import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.io.FileWriter;
import io.akshay.partyinvitation.services.invitation.InvitationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

@Slf4j
@SpringBootApplication
public class InvitationApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(InvitationApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        InvitationService invitationService = applicationContext.getBean(InvitationService.class);
        FileWriter writer = applicationContext.getBean(FileWriter.class);

        try {
            writer.writeFile(invitationService.getInvitees(), getOutputUri());
        } catch (InvitationRuntimeException e) {
            log.error("Error getting customers. Cause: {}", e.toString());
        } catch (IOException e) {
            log.error("Error generating output file, Cause: {}", e.toString());
        }
    }

    private static URI getOutputUri() {
        Path path = Path.of(System.getProperty("user.dir"), "output.json");
        return path.toUri();
    }
}

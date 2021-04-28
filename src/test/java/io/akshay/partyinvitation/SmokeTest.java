package io.akshay.partyinvitation;

import io.akshay.partyinvitation.services.invitation.InvitationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = InvitationApplication.class)
class SmokeTest {

	@Autowired
	private InvitationService invitationService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(invitationService);
	}

}

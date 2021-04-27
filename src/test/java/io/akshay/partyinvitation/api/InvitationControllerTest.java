package io.akshay.partyinvitation.api;

import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.WebApplication;
import io.akshay.partyinvitation.exception.InvitationRuntimeException;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Person;
import io.akshay.partyinvitation.services.invitation.InvitationService;
import io.akshay.partyinvitation.services.invitation.InvitationServiceImpl;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WebApplication.class)
@AutoConfigureMockMvc
class InvitationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvitationService invitationService;

    @Test
    public void getInviteesShouldReturnCustomers() throws Exception {

        List<Customer> sampleCustomers = TestUtils.getSampleCustomers();

        doReturn(sampleCustomers).when(invitationService).getInvitees();

        this.mockMvc
                .perform(get("/invitees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(StringContains.containsString("user_id")));
    }

    @Test
    public void getInviteesShouldReturnErrorWhenExceptionOccurs() throws Exception {

        when(invitationService.getInvitees()).thenThrow(InvitationRuntimeException.class);

        this.mockMvc
                .perform(get("/invitees"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(StringContains.containsString("errorMessage")));
    }

}
package io.akshay.partyinvitation.serialization.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.akshay.partyinvitation.exception.ParseException;
import io.akshay.partyinvitation.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Validated
public class CustomerParser implements Parser<Customer> {

    private ObjectMapper objectMapper;
    private boolean skipInvalid;

    public CustomerParser(@NotNull final ObjectMapper objectMapper,
                          @Value("${customers.data.skipInvalid:true}") final boolean skipInvalid) {
        this.objectMapper = objectMapper;
        this.skipInvalid = skipInvalid;
    }

    /**
     * Parse text to a Customer instance
     *
     * @param text Text value
     * @return {@link Customer}
     * @throws ParseException
     */
    public Customer parse(String text) throws ParseException {
        try {
            return this.objectMapper.readValue(text, Customer.class);
        } catch (JsonProcessingException e) {
            if (skipInvalid) {
                log.error("Failed to parse customer from text..Skipped! Error: {}", e.getMessage());
                return null;
            } else {
                log.error("Failed to parse customer from text [{}]", text);
                throw new ParseException("Error parsing type customer.", e);
            }
        }
    }
}

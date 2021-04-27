package io.akshay.partyinvitation.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import io.akshay.partyinvitation.serialization.views.View;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents customer
 */
@Getter
@Setter
@ToString
public class Customer implements Person {

    /**
     * Customer / User Id
     */
    @JsonView(View.Summary.class)
    @JsonProperty(value = "user_id", required = true)
    private int id;

    /**
     * Name of customer
     */
    @JsonView(View.Summary.class)
    @JsonProperty(required = true)
    private String name;

    /**
     * Holds location of the customer
     */
    @JsonView(View.Details.class)
    @JsonUnwrapped
    private Location location;

    public Customer() {
    }

    @JsonCreator
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

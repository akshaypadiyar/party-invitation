package io.akshay.partyinvitation.models;

/**
 * Generalization of Customer.
 * This is added in case we want other non-customer individuals (eg. Intercom employees, Special guests)
 */
public interface Person {

    /**
     * Get id of the person
     *
     * @return id of the person
     */
    int getId();

    /**
     * Name of the person
     *
     * @return Name
     */
    String getName();
}

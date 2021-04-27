package io.akshay.partyinvitation.repository;

import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.providers.ListProvider;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface CustomerRepository extends ListProvider<Customer> {

    default List<Customer> get() throws Exception {
        return load();
    }

    List<Customer> load() throws Exception;
}

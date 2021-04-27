package io.akshay.partyinvitation;

import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Location;
import io.akshay.partyinvitation.models.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestUtils {

    public static String readFileContents(String path) throws IOException {
        InputStream resourceAsStream = TestUtils.class.getClassLoader().getResourceAsStream(path);
        return new String(resourceAsStream.readAllBytes());
    }

    public static Customer getDummyCustomer() {
        return createCustomer(1, "Dummy", -1, 1);
    }

    public static List<Customer> getSampleCustomers() {
        return List.of(
                createCustomer(1, "Test1", -12.34, 98.12),
                createCustomer(1, "Test2", 45.12, -10.1));
    }

    public static Customer createCustomer(Integer id, String name, double latitude, double longitude) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setLocation(new Location(latitude, longitude));
        return customer;
    }
}

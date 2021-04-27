package io.akshay.partyinvitation.providers;

@FunctionalInterface
public interface Configurer<T> {

    /**
     * Configure the given item
     * @param item
     * @return Configured object
     */
    T configure(T item);
}

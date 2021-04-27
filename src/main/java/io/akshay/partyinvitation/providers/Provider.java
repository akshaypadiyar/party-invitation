package io.akshay.partyinvitation.providers;

/**
 * Generic provider contract
 * @param <T> Return object of type
 */
@FunctionalInterface
public interface Provider<T> {
    /**
     * Only functional method of provider
     * @return
     * @throws Exception
     */
    T get() throws Exception;
}

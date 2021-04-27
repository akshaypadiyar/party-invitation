package io.akshay.partyinvitation.serialization.reader;

import io.akshay.partyinvitation.exception.ParseException;

import java.io.IOException;

@FunctionalInterface
public interface Parser<T> {

    /**
     * Parse text to a object
     * @param value Text value to be parsed
     * @return Return an object of type T
     * @throws IOException
     */
    T parse(String value) throws ParseException;
}

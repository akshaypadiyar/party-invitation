package io.akshay.partyinvitation.serialization.views;

/**
 * Applies 'Views' during serialization
 */
public class View {

    /**
     * Qualifies what is included in 'summary' of an object
     */
    public static class Summary {

    }

    /**
     * Qualifies what is included in the detailed view of an object (includes all summary fields implicitly)
     */
    public static class Details extends Summary {

    }
}

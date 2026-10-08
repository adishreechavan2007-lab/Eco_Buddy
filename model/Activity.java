package com.ecobuddy.model;

import java.time.LocalDate;

/**
 * Abstract base class for anything a user can log.
 * OOP: ABSTRACTION (cannot be instantiated) + ENCAPSULATION (private fields, getters only).
 */
public abstract class Activity {
    private final LocalDate date;
    private final String description;

    protected Activity(String description, LocalDate date) {
        this.description = description == null ? "" : description;
        this.date = date == null ? LocalDate.now() : date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public abstract Category getCategory();

    /** POLYMORPHISM: every subclass overrides this with its own formula. Returns kg CO2e. */
    public abstract double calculateEmission();

    @Override
    public String toString() {
        return String.format("%s | %-9s | %7.2f kg CO2e | %s",
                date, getCategory(), calculateEmission(), description);
    }
}

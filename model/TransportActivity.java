package com.ecobuddy.model;

import java.time.LocalDate;

/** INHERITANCE: extends Activity. */
public class TransportActivity extends Activity {
    private final TransportMode mode;
    private final double distanceKm;

    public TransportActivity(TransportMode mode, double distanceKm, String description, LocalDate date) {
        super(description, date);
        if (mode == null) throw new IllegalArgumentException("mode is required");
        if (distanceKm < 0) throw new IllegalArgumentException("distance cannot be negative");
        this.mode = mode;
        this.distanceKm = distanceKm;
    }

    public TransportActivity(TransportMode mode, double distanceKm) {
        this(mode, distanceKm, mode + " " + distanceKm + " km", null);
    }

    @Override
    public Category getCategory() {
        return Category.TRANSPORT;
    }

    @Override
    public double calculateEmission() {
        return mode.getKgPerKm() * distanceKm;
    }
}

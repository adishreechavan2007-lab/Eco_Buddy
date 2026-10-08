package com.ecobuddy.model;

/**
 * Transport modes with emission factor in kg CO2e per km (per passenger).
 * NOTE: placeholder values. Verify against a public source (DEFRA / EPA / IPCC)
 * and cite it in the README.
 */
public enum TransportMode {
    WALK(0.0), BICYCLE(0.0), BUS(0.10), TRAIN(0.04), MOTORBIKE(0.11), CAR(0.21);

    private final double kgPerKm;

    TransportMode(double kgPerKm) {
        this.kgPerKm = kgPerKm;
    }

    public double getKgPerKm() {
        return kgPerKm;
    }
}

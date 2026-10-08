package com.ecobuddy.model;

/** Energy sources with emission factor in kg CO2e per kWh (placeholder values, cite a source). */
public enum EnergySource {
    SOLAR(0.05), GRID(0.71);

    private final double kgPerKwh;

    EnergySource(double kgPerKwh) {
        this.kgPerKwh = kgPerKwh;
    }

    public double getKgPerKwh() {
        return kgPerKwh;
    }
}

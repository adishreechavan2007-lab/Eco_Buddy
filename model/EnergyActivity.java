package com.ecobuddy.model;

import java.time.LocalDate;

public class EnergyActivity extends Activity {
    private final EnergySource source;
    private final double unitsKwh;

    public EnergyActivity(EnergySource source, double unitsKwh, String description, LocalDate date) {
        super(description, date);
        if (source == null) throw new IllegalArgumentException("source is required");
        if (unitsKwh < 0) throw new IllegalArgumentException("units cannot be negative");
        this.source = source;
        this.unitsKwh = unitsKwh;
    }

    public EnergyActivity(EnergySource source, double unitsKwh) {
        this(source, unitsKwh, source + " " + unitsKwh + " kWh", null);
    }

    @Override
    public Category getCategory() {
        return Category.ENERGY;
    }

    @Override
    public double calculateEmission() {
        return source.getKgPerKwh() * unitsKwh;
    }
}

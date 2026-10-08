package com.ecobuddy.model;

/** Meal types with emission factor in kg CO2e per serving (placeholder values, cite a source). */
public enum MealType {
    VEGAN(1.0), VEGETARIAN(1.5), CHICKEN(1.8), BEEF(6.0);

    private final double kgPerServing;

    MealType(double kgPerServing) {
        this.kgPerServing = kgPerServing;
    }

    public double getKgPerServing() {
        return kgPerServing;
    }
}

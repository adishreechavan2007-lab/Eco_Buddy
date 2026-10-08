package com.ecobuddy.model;

import java.time.LocalDate;

public class FoodActivity extends Activity {
    private final MealType mealType;
    private final int servings;

    public FoodActivity(MealType mealType, int servings, String description, LocalDate date) {
        super(description, date);
        if (mealType == null) throw new IllegalArgumentException("meal type is required");
        if (servings < 0) throw new IllegalArgumentException("servings cannot be negative");
        this.mealType = mealType;
        this.servings = servings;
    }

    public FoodActivity(MealType mealType, int servings) {
        this(mealType, servings, mealType + " x" + servings, null);
    }

    @Override
    public Category getCategory() {
        return Category.FOOD;
    }

    @Override
    public double calculateEmission() {
        return mealType.getKgPerServing() * servings;
    }
}

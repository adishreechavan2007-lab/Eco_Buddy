package com.ecobuddy.test;

import com.ecobuddy.model.*;
import com.ecobuddy.suggestion.SuggestionService;

/** Plain-Java checks so no extra libraries are needed. Run it and look for "ALL TESTS PASSED". */
public class SmokeTest {
    private static int failures = 0;

    private static void check(boolean condition, String name) {
        if (condition) {
            System.out.println("PASS: " + name);
        } else {
            System.out.println("FAIL: " + name);
            failures++;
        }
    }

    public static void main(String[] args) {
        check(Math.abs(new TransportActivity(TransportMode.CAR, 10).calculateEmission() - 2.1) < 1e-9, "car 10km = 2.1 kg");

        Activity[] all = {
            new TransportActivity(TransportMode.BUS, 5),
            new FoodActivity(MealType.BEEF, 1),
            new EnergyActivity(EnergySource.GRID, 5)
        };
        boolean allPositive = true;
        for (Activity a : all) allPositive &= a.calculateEmission() > 0;
        check(allPositive, "polymorphic calculateEmission() works for every subclass");

        boolean threw = false;
        try {
            new TransportActivity(TransportMode.CAR, -1);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check(threw, "negative distance rejected");

        User u = new User("Test");
        SuggestionService s = new SuggestionService();
        check(s.getSuggestion(u).startsWith("Log some"), "no activities -> prompt to log");
        u.addActivity(new TransportActivity(TransportMode.CAR, 10));
        u.addActivity(new FoodActivity(MealType.BEEF, 2));
        check(u.getTopCategory().orElseThrow() == Category.FOOD, "top category is FOOD");
        check(s.getSuggestion(u).startsWith("Food"), "food suggestion returned");
        check(u.getWeeklyTotal() > 0, "weekly total counts today's activities");

        System.out.println(failures == 0 ? "ALL TESTS PASSED" : failures + " TEST(S) FAILED");
        if (failures > 0) System.exit(1);
    }
}

package com.ecobuddy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** COMPOSITION: a User has-a list of Activity objects. ENCAPSULATION: the list is never exposed directly. */
public class User {
    private final String name;
    private final List<Activity> activityLog = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addActivity(Activity activity) {
        if (activity == null) throw new IllegalArgumentException("activity is required");
        activityLog.add(activity);
    }

    public List<Activity> getHistory() {
        return Collections.unmodifiableList(activityLog);
    }

    /** Total emission over the last {@code days} days, including today. */
    public double getTotalForDays(int days) {
        LocalDate cutoff = LocalDate.now().minusDays(days - 1L);
        double total = 0;
        for (Activity a : activityLog) {
            if (!a.getDate().isBefore(cutoff)) {
                total += a.calculateEmission();
            }
        }
        return total;
    }

    public double getWeeklyTotal() {
        return getTotalForDays(7);
    }

    public double getMonthlyTotal() {
        return getTotalForDays(30);
    }

    public Map<Category, Double> getBreakdownByCategory() {
        Map<Category, Double> totals = new EnumMap<>(Category.class);
        for (Activity a : activityLog) {
            totals.merge(a.getCategory(), a.calculateEmission(), Double::sum);
        }
        return totals;
    }

    public Optional<Category> getTopCategory() {
        return getBreakdownByCategory().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}

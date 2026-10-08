package com.ecobuddy;

import com.ecobuddy.model.*;
import com.ecobuddy.suggestion.SuggestionService;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Eco-Buddy: Carbon Footprint Tracker ===");
        System.out.print("Enter your name: ");
        User user = new User(in.nextLine().trim());
        SuggestionService suggestions = new SuggestionService();

        boolean running = true;
        while (running) {
            System.out.println("\n1) Log transport  2) Log food  3) Log energy");
            System.out.println("4) Summary + suggestion  5) History  0) Exit");
            System.out.print("Choose: ");
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> logTransport(user);
                    case "2" -> logFood(user);
                    case "3" -> logEnergy(user);
                    case "4" -> showSummary(user, suggestions);
                    case "5" -> user.getHistory().forEach(System.out::println);
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Bye, " + user.getName() + "!");
    }

    private static <T extends Enum<T>> T pick(Class<T> type, String label) {
        T[] values = type.getEnumConstants();
        System.out.println(label + ":");
        for (int i = 0; i < values.length; i++) {
            System.out.println("  " + (i + 1) + ") " + values[i]);
        }
        System.out.print("Choose: ");
        int idx = Integer.parseInt(in.nextLine().trim()) - 1;
        if (idx < 0 || idx >= values.length) throw new IllegalArgumentException("Invalid choice");
        return values[idx];
    }

    private static double readNumber(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(in.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
    }

    private static void logTransport(User user) {
        TransportMode mode = pick(TransportMode.class, "Transport mode");
        double km = readNumber("Distance in km: ");
        user.addActivity(new TransportActivity(mode, km));
        System.out.println("Logged.");
    }

    private static void logFood(User user) {
        MealType meal = pick(MealType.class, "Meal type");
        int servings = (int) readNumber("Servings: ");
        user.addActivity(new FoodActivity(meal, servings));
        System.out.println("Logged.");
    }

    private static void logEnergy(User user) {
        EnergySource source = pick(EnergySource.class, "Energy source");
        double kwh = readNumber("Units used (kWh): ");
        user.addActivity(new EnergyActivity(source, kwh));
        System.out.println("Logged.");
    }

    private static void showSummary(User user, SuggestionService suggestions) {
        System.out.printf("Weekly total : %.2f kg CO2e%n", user.getWeeklyTotal());
        System.out.printf("Monthly total: %.2f kg CO2e%n", user.getMonthlyTotal());
        for (Map.Entry<Category, Double> e : user.getBreakdownByCategory().entrySet()) {
            System.out.printf("  %-9s %.2f kg%n", e.getKey(), e.getValue());
        }
        System.out.println("Suggestion: " + suggestions.getSuggestion(user));
    }
}

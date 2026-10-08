package com.ecobuddy.suggestion;

import com.ecobuddy.model.Category;
import com.ecobuddy.model.User;

import java.util.EnumMap;
import java.util.Map;

/** Picks the right SuggestionEngine (POLYMORPHISM via the interface) for the user's top category. */
public class SuggestionService {
    private final Map<Category, SuggestionEngine> engines = new EnumMap<>(Category.class);

    public SuggestionService() {
        engines.put(Category.TRANSPORT, new TransportSuggestion());
        engines.put(Category.FOOD, new FoodSuggestion());
        engines.put(Category.ENERGY, new EnergySuggestion());
    }

    public String getSuggestion(User user) {
        return user.getTopCategory()
                .map(c -> engines.get(c).generateSuggestion(user))
                .orElse("Log some activities first and Eco-Buddy will suggest ways to cut your footprint.");
    }
}

package com.ecobuddy.suggestion;

import com.ecobuddy.model.User;

public class FoodSuggestion implements SuggestionEngine {
    @Override
    public String generateSuggestion(User user) {
        return "Food is your biggest source. Swapping a few beef/chicken meals for vegetarian ones makes a big difference.";
    }
}

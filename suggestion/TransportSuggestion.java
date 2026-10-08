package com.ecobuddy.suggestion;

import com.ecobuddy.model.User;

public class TransportSuggestion implements SuggestionEngine {
    @Override
    public String generateSuggestion(User user) {
        return "Transport is your biggest source. Try carpooling, taking the bus/train, or cycling for short trips.";
    }
}

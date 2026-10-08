package com.ecobuddy.suggestion;

import com.ecobuddy.model.User;

public class EnergySuggestion implements SuggestionEngine {
    @Override
    public String generateSuggestion(User user) {
        return "Electricity is your biggest source. Switch off idle devices, use LED lights, and avoid standby power.";
    }
}

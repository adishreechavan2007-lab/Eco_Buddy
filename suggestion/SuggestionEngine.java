package com.ecobuddy.suggestion;

import com.ecobuddy.model.User;

/** Strategy interface: one implementation per category. Add new ones without touching old code. */
public interface SuggestionEngine {
    String generateSuggestion(User user);
}

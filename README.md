# Eco-Buddy 🌱
Carbon Footprint Tracker and Eco Suggestion System — an OOP (Java) mini project.

## Features
- Log daily activities: transport, food, electricity
- Automatic CO2e calculation per activity
- Weekly / monthly totals and per-category breakdown
- Eco-suggestion based on your highest-impact category
- *(Planned)* Swing GUI, save/load to file, leaderboard + streaks

## OOP Concepts Used
| Concept | Where |
|---|---|
| Abstraction | `Activity` abstract class, `SuggestionEngine` interface |
| Inheritance | `TransportActivity`, `FoodActivity`, `EnergyActivity` extend `Activity` |
| Polymorphism | each subclass overrides `calculateEmission()`; `SuggestionService` calls engines through the interface |
| Encapsulation | private fields + getters; `User` returns an unmodifiable history list |
| Composition | `User` has-a list of `Activity` |
| Enums | emission factors live in `TransportMode`, `MealType`, `EnergySource` |

## Project Structure
```
src/com/ecobuddy/
├── Main.java                 # console menu
├── model/                    # Activity hierarchy, User, enums
├── suggestion/               # SuggestionEngine + implementations
└── test/SmokeTest.java       # plain-Java checks (no JUnit needed)
```

## Run (VS Code)
1. Install a JDK (17 or newer) and the "Extension Pack for Java" in VS Code.
2. File → Open Folder → select this folder.
3. Open `src/com/ecobuddy/Main.java` and click **Run** above `main`.

## Run (terminal, from the project folder)
```
javac -d out src/com/ecobuddy/model/*.java src/com/ecobuddy/suggestion/*.java src/com/ecobuddy/test/*.java src/com/ecobuddy/Main.java
java -cp out com.ecobuddy.Main
java -cp out com.ecobuddy.test.SmokeTest
```

## Team
| Member | Responsibility |
|---|---|
| Adishree (member 1)| Activity hierarchy, emission factors (+ cite sources), more activity types |
| Ritika (member 2) | User, suggestions, save/load to file |
| Deven (member 3) | GUI (Swing) and dashboard |

## Emission Factor Sources
TODO: cite the sources for the values in the enums.

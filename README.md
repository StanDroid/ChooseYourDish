# Choose Your Dish

Choose Your Dish is a modern Android application designed to help users discover new meals, browse through various culinary categories, and find inspiration for their next cooking adventure. The app provides detailed meal information, including ingredients, step-by-step instructions, and video guides.

## Features

- **Browse Categories:** Explore a wide variety of meal categories such as Seafood, Vegetarian, Desserts, and more.
- **Meal Search:** Quickly find specific dishes using the search functionality.
- **Random Meal Suggestions:** Can't decide what to eat? Use the "Surprise Me!" feature to get a random dish recommendation.
- **Detailed Meal Info:** View comprehensive details for each meal, including:
    - Ingredients and measurements
    - Cooking instructions
    - Origin area and tags
    - Links to original sources and YouTube tutorials
- **Favorites:** Save your favorite meals for quick access later.
- **Modern UI:** A clean and interactive user interface built with Jetpack Compose, featuring smooth animations powered by Lottie.

## Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/compose)
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Networking:** [Ktor Client](https://ktor.io/docs/client-dependencies.html) with [OkHttp](https://square.github.io/okhttp/)
- **Local Database:** [Room](https://developer.android.com/training/data-storage/room)
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/)
- **Animations:** [Lottie](https://airbnb.io/lottie/#/android)
- **Architecture:** MVVM (Model-View-ViewModel) following Clean Architecture principles.
- **Multi-module project:** Organized into `core`, `data`, `domain`, and `feature` modules for better scalability and maintainability.
- **Performance:** Includes a `:benchmark` module for startup benchmarking and Baseline Profile generation to ensure optimal app performance.

## API Reference

This application uses [TheMealDB API](https://www.themealdb.com/api.php) as its primary data source.

## Project Structure

The project follows a modular architecture:

- **`:app`**: The main entry point of the application, handling navigation and dependency injection setup.
- **`:feature`**: Contains various UI features like `categories`, `search`, `mealdetails`, and `randommeal`.
- **`:domain`**: Pure Kotlin module containing business logic, entities, and use cases.
- **`:data`**: Implements the repositories and handles data sources (Network and Local DB).
- **`:core`**: Shared components including base classes and UI components.

## Getting Started

To run this project:

1. Clone the repository.
2. Open the project in Android Studio (Ladybug or newer recommended).
3. Build and run the `app` module on an emulator or a physical device.

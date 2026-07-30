# Architecture

## Module Architecture

The project is organized as a **multi-module Gradle project** following Clean Architecture layering. Each module has a single, well-defined responsibility. Dependency flow is strictly top-down: `:app` → `:feature:*` → `:domain` → `:data:*` → `:core:*`.

```text
:app
 ├── :feature:categories        — Browse meal categories screen
 ├── :feature:categorymeals     — Meals list for a selected category
 ├── :feature:mealdetails       — Full detail view for a single meal
 ├── :feature:randommeal        — "Surprise Me" random meal screen
 ├── :feature:search            — Full-text meal search screen
 └── :feature:aichat            — AI-powered chat assistant (Gemini)

 ├── :domain                    — Pure business logic: Use Cases, Repository interfaces, Entities
 
 ├── :data:categories           — Remote + local data source for categories
 ├── :data:ingredients          — Ingredient data source
 ├── :data:meal                 — Meal repository implementation
 ├── :data:aichat               — AI chat data source / Gemini integration
 ├── :data:network              — Shared Ktor HTTP client setup & interceptors
 └── :data:db                   — Room database, DAOs, and entities

 ├── :core:base                 — Shared base classes (BaseViewModel, Result wrappers, extensions)
 ├── :core:ui                   — Shared Compose components, design tokens, theme
 └── :core:testing              — Shared test utilities, fakes, and test rules

 └── :benchmark                 — Macrobenchmark module for startup & Baseline Profile generation
```

### Layer Responsibilities

| Layer | Module(s) | Allowed Dependencies |
|---|---|---|
| **App** | `:app` | All feature, data, domain, core modules |
| **Feature** | `:feature:*` | `:domain`, `:core:ui`, `:core:base` |
| **Domain** | `:domain` | `:core:base` only. **No Android framework.** |
| **Data** | `:data:*` | `:domain`, `:core:base`, `:data:network`, `:data:db` |
| **Core** | `:core:*` | Only other `:core:*` modules where needed |
| **Benchmark** | `:benchmark` | `:app` (benchmark build type) |

> **Rule:** Feature modules must **never** depend on other feature modules directly. All cross-feature communication goes through `:domain` use cases or Navigation.

## Architecture Pattern

The project follows **MVVM + Clean Architecture**:

```
UI (Composable) ──▶ ViewModel ──▶ UseCase ──▶ Repository (interface in :domain)
                                                    │
                                         RepositoryImpl (in :data:*)
                                                    │
                                         Remote Source (Ktor) + Local Source (Room)
```

- **State management:** `StateFlow` / `SharedFlow` exposed from ViewModel. Composables collect state using `collectAsStateWithLifecycle()`.
- **Error handling:** A sealed `Result<T>` wrapper (defined in `:core:base`) propagates success/failure through the layers.
- **Navigation:** Single-activity app using `Navigation Compose` (`2.9.7`). The nav graph is assembled in `:app`.
- **DI Graph:** Hilt modules are defined per data module and feature module. The `:app` module provides the root component.

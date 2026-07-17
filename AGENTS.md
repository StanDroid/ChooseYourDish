# ChooseYourDish — Agent Rules & Project Boundaries

> **Scope**: This file is the authoritative reference for all AI agents operating in this workspace.
> Every agent, expert, or subagent **must** read this file before taking any action.

---

## 1. Project Overview

**ChooseYourDish** is a modern Android application that helps users discover meals, browse culinary categories, search for specific dishes, get random meal suggestions, and interact with an AI-powered chat assistant. The app fetches live data from [TheMealDB API](https://www.themealdb.com/api.php) and persists favourites in a local Room database.

- **Application ID:** `com.cyd`
- **Version:** `0.11` (versionCode `11`)
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target / Compile SDK:** 36 (Android 16)
- **Java Compatibility:** `VERSION_21`

---

## 2. Tech Stack & Exact Versions

All versions are managed via the **Gradle Version Catalog** at [`gradle/libs.versions.toml`](gradle/libs.versions.toml). Never hard-code a version string; always reference a catalog alias.

### 2.1 Core Language & Build

| Tool | Version |
|---|---|
| Kotlin | `2.3.20` |
| Kotlin Compiler Plugin (`plugin-kotlin`) | `2.2.10` |
| KSP (Kotlin Symbol Processing) | `2.2.0-2.0.2` |
| AGP (Android Gradle Plugin) | `9.2.1` |
| Gradle Wrapper | `9.2.1` |
| Java Toolchain | `VERSION_21` |
| KtLint Gradle | `14.2.0` |
| JaCoCo | `0.8.14` |

### 2.2 Jetpack Compose & UI

| Library | Version |
|---|---|
| Compose UI (`androidx.compose.ui`) | `1.10.6` |
| Compose Animation | `1.10.6` |
| Material 3 | `1.4.0` |
| Material 3 Window Size Class | `1.4.0` |
| Compose Kotlin Plugin (`kotlin.plugin.compose`) | `2.3.20` |
| Activity Compose | `1.13.0` |
| Navigation Compose | `2.9.7` |
| Hilt Navigation Compose | `1.3.0` |

### 2.3 Lifecycle & Architecture

| Library | Version |
|---|---|
| Lifecycle Runtime KTX | `2.10.0` |
| Lifecycle Runtime Compose | `2.10.0` |
| Lifecycle ViewModel KTX | `2.10.0` |
| Lifecycle Extensions (legacy) | `1.1.1` |

### 2.4 Dependency Injection

| Library | Version |
|---|---|
| Hilt Android | `2.59.2` |
| Hilt Compiler (KSP) | `2.59.2` |

### 2.5 Networking

| Library | Version |
|---|---|
| Ktor Client Core | `2.3.12` |
| Ktor Client OkHttp | `2.3.12` |
| Ktor Client Content Negotiation | `2.3.12` |
| Ktor Serialization Gson | `2.3.12` |
| Ktor Client Mock (test) | `2.3.12` |
| OkHttp URL Connection | `5.3.2` |
| OkHttp Logging Interceptor | `5.3.2` |
| KotlinX Serialization JSON | `1.11.0` |

### 2.6 Local Database

| Library | Version |
|---|---|
| Room Runtime | `2.8.4` |
| Room KTX | `2.8.4` |
| Room Compiler (KSP) | `2.8.4` |

### 2.7 Image Loading

| Library | Version |
|---|---|
| Coil Compose | `2.7.0` |
| Coil GIF | `2.7.0` |

### 2.8 Animations

| Library | Version |
|---|---|
| Lottie Compose (Airbnb) | `6.7.1` |

### 2.9 Firebase

| Library | Version |
|---|---|
| Firebase BOM | `34.12.0` |
| Firebase Crashlytics | _managed by BOM_ |
| Firebase Analytics | _managed by BOM_ |
| Firebase Crashlytics Gradle Plugin | `3.0.7` |
| Google Services Plugin | `4.4.4` |

### 2.10 AI

| Library | Version |
|---|---|
| Google Generative AI (Gemini) | `0.9.0` |

### 2.11 Async

| Library | Version |
|---|---|
| KotlinX Coroutines Android | `1.10.2` |
| KotlinX Coroutines Core | `1.10.2` |
| KotlinX Coroutines Test | `1.10.2` |

### 2.12 Testing

| Library | Version |
|---|---|
| JUnit 4 | `4.13.2` |
| AndroidX JUnit Extension | `1.3.0` |
| Espresso Core | `3.7.0` |
| Compose UI Test JUnit4 | `1.10.6` |
| UIAutomator | `2.3.0` |
| MockK | `1.14.2` |
| Turbine (Flow testing) | `1.2.0` |

### 2.13 Debug / Quality

| Library | Version |
|---|---|
| LeakCanary | `2.14` |
| KtLint Gradle | `14.2.0` |
| Benchmark Macro JUnit4 | `1.4.1` |
| Profile Installer | `1.4.1` |

---

## 3. Module Architecture

The project is organized as a **multi-module Gradle project** following Clean Architecture layering. Each module has a single, well-defined responsibility. Dependency flow is strictly top-down: `:app` → `:feature:*` → `:domain` → `:data:*` → `:core:*`.

```
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

---

## 4. Architecture Pattern

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

---

## 5. Build Configuration

| Setting | Value |
|---|---|
| Gradle Configuration Cache | ✅ Enabled |
| Gradle Build Cache | ✅ Enabled |
| Parallel Builds | ✅ Enabled |
| Non-transitive R classes | ✅ Enabled |
| R8 Full Mode | ✅ Enabled |
| R8 Optimized Resource Shrinking | ✅ Enabled |
| JVM Heap (Gradle Daemon) | `-Xmx4096m` |
| Code Style | `kotlin.code.style=official` |
| Lint (deprecation) | `-Xlint:deprecation` on all `JavaCompile` tasks |

### Build Types

| Type | Minified | Debuggable | Coverage |
|---|---|---|---|
| `debug` | ❌ | ✅ | ✅ (unit + instrumented) |
| `release` | ✅ (R8 + resource shrinking) | ❌ | ❌ |
| `benchmark` | ✅ (with `benchmark-rules.pro`) | ❌ | ❌ |

---

## 6. Code Quality & Style

- **KtLint** is applied to **all** modules via the root build script. Always run `./gradlew ktlintCheck` before submitting changes.
- **No Stubs:** Never output placeholder methods or `// TODO` comments. All generated code must be fully functional.
- **Idiomatic Kotlin:** Use trailing lambdas, scope functions (`let`, `apply`, `run`, `also`), and `when` expressions. Prefer `val` over `var`.
- **Visibility:** Always use the most restrictive visibility modifier (`private`, `internal`).
- **Coroutines:** Never use `GlobalScope`. Always use `viewModelScope`, `lifecycleScope`, or an injected `CoroutineDispatcher`.
- **Compose state:** Never hold UI state in a `MutableState` inside a `ViewModel`. Use `StateFlow`.

---

## 7. Global Execution Guardrails

1. **Read before writing:** Before editing a file, read the file and understand its full context.
2. **Minimal diffs:** Only output the specific code blocks being changed, never rewrite an entire file.
3. **Compile verification:** Before declaring any task complete, run `./gradlew assembleDebug -q` and confirm it exits with code `0`.
4. **Non-interactive commands:** Never run commands that prompt for user input.
5. **Version Catalog first:** All new dependencies must be added to `gradle/libs.versions.toml` before referencing them in a module's `build.gradle.kts`.
6. **KSP over KAPT:** Use `ksp(...)` for all annotation processors. Do not introduce `kapt` in new modules (legacy `:domain` module still uses `kapt`; migrate when touching it).

---

## 8. Subagent & Domain Expert Routing

Spawn or refer to the appropriate expert for specialized tasks:

| Domain | Expert File | Trigger Condition |
|---|---|---|
| Compose UI, Layouts, Insets, Animations | `.agents/experts/compose-expert.md` | Adding/modifying Composables, theming, navigation UI |
| Async, Coroutines, StateFlow, SharedFlow | `.agents/experts/coroutine-expert.md` | ViewModel state, Flow operators, dispatcher selection |
| Networking, Repositories, Data Sources | `.agents/experts/data-layer-expert.md` | Ktor clients, Room DAOs, repository implementations |
| Unit & UI Testing | `.agents/experts/qa-expert.md` | Writing tests, running coverage, MockK setup |
| Feature Architecture Planning | `.agents/experts/architect.md` | New feature design, module boundaries, DI graph |

---

## 9. Task Initiation & Lifecycle

1. Before writing any code, create or read the localized work item using the template at `.agents/scope/feature-scope-template.md`.
2. **Scope File Location:** All Feature Specification Scope documents created from the template **must** be saved to `.agents/scope/active/<feature-name>.md`. This folder is listed in `.gitignore` and will **never** be committed to version control. Never save scope files directly in `.agents/scope/` alongside the template.
3. The work item must define: **goal**, **affected modules**, **acceptance criteria**, and **verification command**.
4. Mark tasks in-progress, then complete. Never leave tasks in an ambiguous state.

---

## 10. Multi-Agent Workflows

For complex, end-to-end features that span multiple layers, use the phased hand-off protocol:

**Protocol:** `.agents/workflows/feature-studio.md`

| Phase | Owner | Deliverable |
|---|---|---|
| **Phase 1 — Design** | `architect.md` | Code plan, module graph, API contracts |
| **Phase 2 — UI** | `compose-expert.md` | Composables, ViewModel state, navigation wiring |
| **Phase 2 — Data** | `data-layer-expert.md` | Repository, DAO, Ktor endpoint, Hilt module |
| **Phase 3 — Verify** | `qa-expert.md` | Unit tests, UI tests, `assembleDebug` green |

> Each phase must be fully complete and verified before the next phase begins. No partial hand-offs.

---

## 11. API Reference

| Source | Details |
|---|---|
| **Primary data source** | [TheMealDB API](https://www.themealdb.com/api.php) — free, no auth required for v1 |
| **AI Chat** | Google Gemini via `com.google.ai.client.generativeai` SDK `0.9.0` |
| **Analytics / Crash** | Firebase Analytics + Crashlytics (BOM `34.12.0`) |
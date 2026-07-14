---
name: coroutines-flow
description: "Enforces non-blocking asynchronous programming using Kotlin Coroutines and StateFlow/SharedFlow execution patterns."
---

# Kotlin Coroutines & Flow Execution Standards

## 1. Threading & Dispatchers
- **Never block the Main Thread:** All network requests (Ktor) and database disk operations (Room) must be explicitly executed on `Dispatchers.IO`.
- **Context Switching:** Use `withContext(Dispatchers.IO)` inside repositories or data sources. Never force a ViewModel to choose a dispatcher for data loading.

## 2. Jetpack Compose Flow Collection
- **Lifecycle-Aware Collection:** Always collect `StateFlow` streams in Compose layouts using `.collectAsStateWithLifecycle()` from the `lifecycle-runtime-compose` library.
- **Avoid `.collectAsState()`:** Standard collection leaks resources when the app goes into the background; always enforce the lifecycle-aware alternative.

## 3. Scope Management
- Always use `viewModelScope` inside ViewModels so async operations cancel automatically when the screen is cleared.
- Never use `GlobalScope` under any circumstances.
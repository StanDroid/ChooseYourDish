---
name: ktor-network
description: "Provides configurations for robust API clients using Ktor, emphasizing explicit type-safety and error handling."
---

# Ktor Network Engineering Guidelines

## 1. Safe API Calls
- Every network request call must be wrapped in a type-safe result wrapper (e.g., a sealed interface `Resource<T>` or Kotlin's native `Result<T>`).
- Explicitly catch `ClientRequestException`, `ServerResponseException`, and `ConnectException`.

## 2. Serialization Architecture
- Enforce the use of **Kotlinx Serialization** with explicit `@Serializable` data transfer objects (DTOs).
- Keep API DTO modules strictly inside the `data` layer. Convert DTOs to pure domain entities using mapper functions before passing them upward.
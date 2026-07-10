# Kotlin Coroutines Conventions

- **Safe Collection:** In Compose UI, collect Flows lifecycle-safely using `collectAsStateWithLifecycle()`. Never use the standard `.collectAsState()`.
- **ViewModel Scope:** Always execute async launch routines within the scope of `viewModelScope`.
- **Dispatcher Hard Rule:** Main-thread safety is mandatory. Repositories must switch context to `Dispatchers.IO` using `withContext` before executing any network or database transaction.
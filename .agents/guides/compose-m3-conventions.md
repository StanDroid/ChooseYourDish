# Jetpack Compose & Material 3 Conventions

- **Edge-to-Edge:** Always call `enableEdgeToEdge()` in the main activity's `onCreate()` before `setContent`.
- **System Insets:** Always apply the standard `innerPadding` exposed by the `Scaffold` component to your root container's modifier to correctly offset status and navigation bars.
- **UDF Pattern:** Screen state must be collected as an immutable `StateFlow<UiState>` emitted from a `ViewModel`. 
- **Preview Stability:** Always provide `@Preview` definitions for every UI element, utilizing Compose preview parameter providers where applicable.
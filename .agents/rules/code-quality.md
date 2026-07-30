# Code Quality & Style

For universal code style and terminal execution guardrails (such as No Stubs and Compile Verification), see `@.agents/rules/android-engineer.md`.

- **KtLint** is applied to **all** modules via the root build script. Always run `./gradlew ktlintCheck` before submitting changes.
- **Visibility:** Always use the most restrictive visibility modifier (`private`, `internal`).
- **Coroutines:** Never use `GlobalScope`. Always use `viewModelScope`, `lifecycleScope`, or an injected `CoroutineDispatcher`.
- **Compose state:** Never hold UI state in a `MutableState` inside a `ViewModel`. Use `StateFlow`.

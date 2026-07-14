# ChooseYourDish - Project Rules & Boundaries

This project is an Android application built with Jetpack Compose, Material 3, and modern Android architecture components.

## 1. Project Directory & Stack Map
- **Language:** Kotlin (Modern, idiomatic style, trailing lambdas)
- **UI:** Jetpack Compose with Material 3
- **Data Layers:** Room DB & Ktor Network client
- **DI:** Hilt

## 2. Global Execution Guardrails
- **No Stubs:** Never output stub methods or placeholders like `// TODO`. Implement fully functional logic blocks.
- **Verification:** Before completing a work item, you must verify local compilation by running `./gradlew assembleDebug`.

## 3. Subagent & Domain Experts Routing
When executing tasks in specialized boundaries, spawn or refer to these relative domain profiles:
- **Compose Layouts & Insets:** Refer to @.agents/experts/compose-expert.md
- **Async Concurrency & Data Flow:** Refer to @.agents/experts/coroutine-expert.md
- **Network & Repositories:** Refer to @.agents/experts/data-layer-expert.md
- **Local Testing Workflows:** Refer to @.agents/experts/qa-expert.md

## 4. Task Initiation & Lifecycle (Scope)
- Before modifying or adding code, the active expert must create or read a localized work item file tracking the task state.
- Use the markdown blueprint defined in `@.agents/scope/feature-scope-template.md` to establish the definition of done before executing code changes.

## 5. Multi-Agent Workflows
- For complex, end-to-end features, do not work in a single thread. Follow the phased hand-off protocol outlined in `@.agents/workflows/feature-studio.md`.
- Phase 1 (Design): `architect.md` establishes the code plan.
- Phase 2 (Code): `compose-expert.md` (UI) and `data-layer-expert.md` (State/DB) execute implementation.
- Phase 3 (Verify): `qa-expert.md` validates tests and runs compilation.
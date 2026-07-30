# ChooseYourDish — Agent Rules & Project Boundaries

> **Scope**: This file is the authoritative reference for all AI agents operating in this workspace.
> Every agent, expert, or subagent **must** read this file before taking any action.

---

## 1. Project Overview

**ChooseYourDish** is a modern Android application that helps users discover meals, browse culinary categories, search for specific dishes, get random meal suggestions, and interact with an AI-powered chat assistant. The app fetches live data from [TheMealDB API](https://www.themealdb.com/api.php) and persists favourites in a local Room database.

- **Application ID:** `com.cyd`
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target / Compile SDK:** 36 (Android 16)
- **Java Compatibility:** `VERSION_21`

## Harness Architecture Definition
This project strictly enforces the five essential subsystems of an Agent Harness to ensure deterministic and reproducible workflows:
- **Instructions:** `AGENTS.md` and the `.agents/` directory dictate all behavioral rules and context.
- **Tools:** The Gradle wrapper (`./gradlew`) is the primary execution tool for compilation, linting, and testing.
- **Environment:** Must be validated via the `./init.sh` script (Java 21, Android SDK configuration, Gradle cache).
- **State:** Global features are tracked in `feature_list.json`, and active session context is tracked in `agent-progress.md` to prevent context loss.
- **Feedback:** Compilation (`assembleDebug`) and test results must be verified and logged before marking any task as complete.

---

## 2. Topic Docs & Routing

> **CRITICAL CONTEXT RULE:** Do NOT read all these files by default. To save context budget (tokens), **ONLY open the relevant directories/files below IF you specifically need that information for your current task.**

- **Tech Stack & Versions:** `.agents/rules/tech-stack.md` (Read when modifying `build.gradle.kts` or adding dependencies)
- **Architecture & Modules:** `.agents/rules/architecture.md` (Read when creating new features, modules, or checking layer dependencies)
- **Build Configurations:** `.agents/rules/build-config.md` (Read when diagnosing build issues or modifying R8/Proguard)
- **Code Quality & Style:** `.agents/rules/code-quality.md` (Read before writing new code to check style constraints)
- **API Reference:** `.agents/rules/api-reference.md` (Read when integrating network calls or Firebase)

For universal Android engineering guardrails, you MUST follow `@.agents/rules/android-engineer.md`.

---

## 3. Global Execution Guardrails

1. **Read before writing:** Before editing a file, read the file and understand its full context.
2. **Minimal diffs:** Only output the specific code blocks being changed, never rewrite an entire file.
3. **Compile verification:** Before declaring any task complete, run `./gradlew assembleDebug -q` and confirm it exits with code `0`.
4. **Non-interactive commands:** Never run commands that prompt for user input.
5. **Version Catalog first:** All new dependencies must be added to `gradle/libs.versions.toml` before referencing them in a module's `build.gradle.kts`.
6. **KSP over KAPT:** Use `ksp(...)` for all annotation processors. Do not introduce `kapt` in new modules (the legacy `:domain` module still uses `kapt` specifically for the Hilt compiler; migrate to KSP when touching it to avoid confusion).

---

## 4. Subagent & Domain Expert Routing

Spawn or refer to the appropriate expert for specialized tasks:

| Domain | Expert File | Trigger Condition |
|---|---|---|
| Compose UI, Layouts, Insets, Animations | `.agents/experts/compose-expert.md` | Adding/modifying Composables, theming, navigation UI |
| Async, Coroutines, StateFlow, SharedFlow | `.agents/experts/coroutine-expert.md` | ViewModel state, Flow operators, dispatcher selection |
| Networking, Repositories, Data Sources | `.agents/experts/data-layer-expert.md` | Ktor clients, Room DAOs, repository implementations |
| Unit & UI Testing | `.agents/experts/qa-expert.md` | Writing tests, running coverage, MockK setup |
| Feature Architecture Planning | `.agents/experts/architect.md` | New feature design, module boundaries, DI graph |
| Core & Domain layers | `.agents/experts/architect.md` | Modifications to `:core:*` or `:domain` |

---

## 5. Task Initiation & Lifecycle

1. Before writing any code, verify the environment using `./init.sh` (Mac/Linux) or `./init.ps1` (Windows).
2. **Session Reset Protocol:** Open `agent-progress.md` and overwrite the "Active Session" block with the current date, active feature, and phase to clear old context.
3. Update `feature_list.json` by setting the target feature's status to `in-progress`.
4. Create or read the localized work item using the template at `.agents/scope/feature-scope-template.md`.
5. **Scope File Location:** All Feature Specification Scope documents created from the template **must** be saved to `.agents/scope/active/<feature-name>.md`. This folder is listed in `.gitignore` and will **never** be committed to version control. Never save scope files directly in `.agents/scope/` alongside the template.
6. The work item must define: **goal**, **affected modules**, **acceptance criteria**, and **verification command**.
7. Mark tasks in-progress, then complete. When finished, update `feature_list.json` to mark the feature as `done`. Never leave tasks in an ambiguous state.

---

## 6. Multi-Agent Workflows

For complex, end-to-end features that span multiple layers, use the phased hand-off protocol:
[AGENTS.md](AGENTS.md)
**Protocol:** `.agents/workflows/feature-studio.md`

| Phase | Owner | Deliverable |
|---|---|---|
| **Phase 1 — Design** | `architect.md` | Code plan, module graph, API contracts |
| **Phase 2 — UI** | `compose-expert.md` | Composables, ViewModel state, navigation wiring |
| **Phase 2 — Data** | `data-layer-expert.md` | Repository, DAO, Ktor endpoint, Hilt module |
| **Phase 3 — Verify** | `qa-expert.md` | Unit tests, UI tests, `assembleDebug` green |

> Each phase must be fully complete and verified before the next phase begins. No partial hand-offs.

## Work Rules
- Work on one feature at a time
- Only start the next feature after the current one passes end-to-end verification
- Don't "also refactor" feature B while implementing feature A
---
trigger: always_on
---

---
name: android-engineer
description: "Applies global Kotlin code quality standards and safe terminal execution guardrails across the workspace."
activation: Always On
---

# Global Android Engineering Guardrails

These guidelines apply universally to any chat, task, or expert subagent active in this workspace.

## 1. Code Completeness & Style
- **No Placeholders:** Never generate partial code or write comments like `// TODO: Implement later`. All generated code blocks must be fully written, compilable, and functional.
- **Idiomatic Kotlin:** Write clean, idiomatic Kotlin utilizing trailing lambdas, scope functions (`apply`, `let`), and explicit visibility modifiers.

## 2. Terminal & Build Safety
- **Verification First:** Before declaring any coding task complete, you must run local compilation verification using `./gradlew assembleDebug`.
- **Quiet Execution:** When executing terminal commands, always append quiet flags if available to prevent excessive console logs.
- **Non-Interactive:** Never execute commands that require interactive user input (e.g., `yes/no` prompts).

## 3. Token & Output Economy
- **Code Modifications:** When editing an existing file, never rewrite the whole file. You must output *only* the specific target code blocks or use a clean unified diff format (`// ... existing code ...`).
- **Context Awareness:** Only read the specific file requested or files directly imported by it. Avoid sweeping multi-directory codebase searches unless explicitly commanded.
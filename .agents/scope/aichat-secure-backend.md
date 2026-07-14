# Feature Specification Scope: AI Chat Secure Backend Boundary

## 1. Requirements Overview
- Remove direct Gemini API key usage from the Android app.
- Replace direct Gemini SDK calls with a repository-backed backend API call.
- Keep the feature disabled with a clear user-facing error when no backend URL is configured.

## 2. Technical Stack Checklist
- [ ] UI Design System: Existing Compose screen only, no layout redesign.
- [ ] Local Storage (Room): Not applicable.
- [x] Network Integrations: Linked to @../guides/ktor-network-conventions.md
- [x] Concurrency/Async: Linked to @../guides/coroutine-conventions.md
- [x] Hilt Dependency Injection: Linked to @../guides/hilt-di-conventions.md
- [x] MultiThreading: Linked to @../guides/coroutine-conventions.md

## 3. Active Experts Assigned
- [ ] architect
- [ ] compose-expert
- [x] coroutine-expert
- [x] data-layer-expert
- [x] qa-expert

## 4. Verification Check
- Compile: `./gradlew assembleDebug`
- Unit verification: `./gradlew testDebugUnitTest`

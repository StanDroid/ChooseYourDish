---
description: 
---

# /feature-lifecycle

## Step 1: Design Phase
Spawn the **architect** subagent. Feed it the user's initial feature request.
Instruct the architect to inspect the codebase, build a comprehensive implementation plan, and write it to `docs/plans/active-plan.md`.
Wait for the architect to complete.

## Step 2: Implementation Phase
Spawn the **engineer** subagent. Give it access to `docs/plans/active-plan.md`.
Instruct the engineer to implement the Kotlin/Compose/Hilt files sequentially following the checklist.
Once written, the engineer must compile the project using `./gradlew assembleDebug` to make sure it is syntactically sound.
Wait for the engineer to complete.

## Step 3: Test and Verify
Spawn the **qa-specialist** subagent.
Instruct the qa-specialist to read the changes made by the engineer, write robust Unit and UI tests (Robolectric), and execute `./gradlew testDebugUnitTest`.
If the tests fail, bubble up the trace to the engineer to fix. If they pass, report success to the user.
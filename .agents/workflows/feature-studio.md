---
description: 
---

# /feature-studio
An automated, state-controlled SDLC pipeline optimized for token economy.

## Step 1: Structural Design
Spawn the **architect** expert.
cache: true
Instruct the architect to analyze the task, initialize `docs/active_state.md`, and write the engineering plan.
Wait for architect to finish.

## Step 2: Specialized Implementation
Spawn the required development expert based on the task:
- For UI tasks: Spawn the **compose-expert**.
- For Async tasks: Spawn the **coroutine-expert**.
cache: true  # This pins the parent rules/android-engineer.md and guides in memory
Instruct the active expert to read the plan and modify files using targeted snippets.
Wait for the implementation to finish.

## Step 3: Test & Verification
Spawn the **qa-expert** expert.
cache: true
Instruct the qa-expert to read the state ledger and execute local validation.
Wait for qa-expert to finish.
---
description: 
---

# /feature-studio
An automated, state-controlled SDLC pipeline optimized for token economy.

## Step 0: Environment Verification
Verify the environment using `./init.sh` (Mac/Linux) or `./init.ps1` (Windows).
Ensure `feature_list.json` status is set to `in-progress`.

## Step 1: Structural Design
Spawn the **architect** expert.
cache: true
Instruct the architect to analyze the task, initialize `scope/active/<feature-name>.md`, and write the engineering plan.
Wait for architect to finish.

## Step 2: Specialized Implementation
Spawn the required development expert based on the task:
- For UI tasks: Spawn the **compose-expert**.
- For Async tasks: Spawn the **coroutine-expert**.
- For Data/Network tasks: Spawn the **data-layer-expert**.
cache: true  # This pins the parent rules/android-engineer.md and guides in memory
Instruct the active expert to read the plan and modify files using targeted snippets.
Wait for the implementation to finish.

## Step 3: Test & Verification
Spawn the **qa-expert** expert.
cache: true
Instruct the qa-expert to read the state ledger and execute local validation.
If tests fail, bubble up the trace to the active expert from Step 2 to fix (rollback/error-recovery protocol).
Wait for qa-expert to finish.
When finished, mark feature as `done` in `feature_list.json`.
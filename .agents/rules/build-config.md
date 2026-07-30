# Build Configuration

| Setting | Value |
|---|---|
| Gradle Configuration Cache | ✅ Enabled |
| Gradle Build Cache | ✅ Enabled |
| Parallel Builds | ✅ Enabled |
| Non-transitive R classes | ✅ Enabled |
| R8 Full Mode | ✅ Enabled |
| R8 Optimized Resource Shrinking | ✅ Enabled |
| JVM Heap (Gradle Daemon) | `-Xmx4096m` |
| Code Style | `kotlin.code.style=official` |
| Lint (deprecation) | `-Xlint:deprecation` on all `JavaCompile` tasks |

### Build Types

| Type | Minified | Debuggable | Coverage |
|---|---|---|---|
| `debug` | ❌ | ✅ | ✅ (unit + instrumented) |
| `release` | ✅ (R8 + resource shrinking) | ❌ | ❌ |
| `benchmark` | ✅ (with `benchmark-rules.pro`) | ❌ | ❌ |

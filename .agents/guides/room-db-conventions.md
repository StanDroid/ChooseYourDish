# Room Database Conventions

- **Threading:** All Room query operations must utilize Kotlin Coroutines (`suspend` functions) or return observable `Flow<T>` streams.
- **Migrations:** Never modify database schemas without providing an explicit `Migration` object or utilizing auto-migrations. Increment schema versions sequentially.
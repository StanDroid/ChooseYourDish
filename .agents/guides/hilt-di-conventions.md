# Hilt Dependency Injection Conventions

- **Constructors:** All injectable dependencies must declare explicit `@Inject constructor()`.
- **ViewModels:** All ViewModels must use `@HiltViewModel` annotations.
- **Annotations:** Always annotate Activities/Fragments with `@AndroidEntryPoint` when injecting dependencies.
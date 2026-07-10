# Ktor Network Conventions

- **Data Models:** All remote response models (DTOs) must use `@Serializable` and stay strictly isolated inside the network/data modules.
- **Mappers:** You must map DTO objects into clean Domain Entities before passing data up to the domain or UI layers.
- **Resilience:** Wrap Ktor HTTP client executions in safety `try/catch` blocks targeting standard HTTP response exceptions (`ClientRequestException`, `ServerResponseException`).
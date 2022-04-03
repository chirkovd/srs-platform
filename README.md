# SRS-Platform
Application for the Search and Rescue Squad.

# Environments
Use docker compose file with predefined PostgresQl, RabbitMq and Vault

Profiles:
1. local - use default properties
2. vault - use properties from Vault instance (http://localhost:8200/ui)

Application API is available on http://localhost:8080

# Monitoring
Grafana with Prometheus are available for monitoring.
1. http://localhost:9090 - Prometheus
2. http://localhost:3000 - Grafana
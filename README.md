# SRS-Platform
Application for the Search and Rescue Squad.

# Environments
Use docker compose file with predefined PostgresQl, RabbitMq and Vault

Profiles:
    local - use default properties
    vault - use properties from Vault instance (http://localhost:8200)

Application API is available on http://localhost:8080/api
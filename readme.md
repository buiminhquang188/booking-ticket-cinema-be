# Booking Ticket Cinema

1. Install dependencies using maven cli.
2. Create environment variables by using command (Or copy file .env.properties.example and paste, then rename it):

```bash
cp .env.properties.example .env.properties
```

3. Replace variable value inside file `.env.properties`, which will create database and password for next step.
4. Run command:

```bash
docker compose --profile database create --pull missing --no-recreate
```

This command will run file `docker-compose.yml` in directory.

5. Run command:

```bash
docker compose --profile database start
```

This will start database.
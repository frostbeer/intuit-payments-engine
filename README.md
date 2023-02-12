# Intuit Payments Risk Engine POC
This a POC of the payments risk engine

This POC reads from Kafka payments and runs a mock risk analysis based on Random.

### DB
Currently, the engine doesnt write the payments and their risk to any DB but this could be easily integrated to the POC.
Using a PostgreSQL DB with the table PaymentsRisks

| userId | payeeId | amount | createdAt | isRisk | 
| ----------- | ----------- | ----- | ----- | ----- |
| 11111 | 1234 | 12.4 | 2023-02-10T10:10:12 | true |

PostgreSQL DB create table query
```sql
CREATE TABLE payments_risks (
	user_id VARCHAR(50) NOT NULL,
	payee_id VARCHAR(50) NOT NULL,
	amount DECIMAL(8,2) DEFAULT 0.0,
	created_at TIMESTAMP NOT NULL,
    is_risky BOOLEAN NOT NULL,
    primary key (user_id, payee_id, created_at)
);
create index index_payments_risks_on_created_at on payments_risks (created_at);
```

### Running Locally
You can run the engine together with Kafka using the docker-compose.
There is also Kafka UI in localhost:8080

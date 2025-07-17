# customerDataGenerator

A Java Spring Boot application for generating and recreating customer data for utility companies.

## Environment Setup

### 1. Create Environment Variables

Copy the example environment file and configure your credentials:

```bash
cp .env.example .env
```

### 2. Configure AWS Credentials

Edit the `.env` file and add your AWS credentials:

```bash
# AWS Credentials
AWS_ACCESS_KEY_ID=your_aws_access_key_here
AWS_SECRET_ACCESS_KEY=your_aws_secret_key_here

# SQS Configuration
SQS_QUEUE_URL=https://sqs.us-east-1.amazonaws.com/your-account-id/your-queue-name

# SFTP Configuration
SFTP_REMOTE_BOX=your-sftp-server.compute-1.amazonaws.com
SFTP_COMMAND=/opt/bidgely/ingesterClient/sh/sftpRawFileManager run -file_type raw_data_file
```

### 3. Configuration

The application uses environment variables for sensitive configuration. Key configuration parameters include:

- **usersList**: List of user UUIDs to process (now provided directly in config instead of reading from file)
- **billCycleCode**: Billing cycle code for the environment
- **userPrefFile**: Template file for user preferences (processed with variable substitution)
- **inputMeterFuel**: Comma-separated meter fuel types (e.g., "AMI-ELECTRIC,AMI-GAS")

### 4. How It Works

The application automatically loads environment variables from the `.env` file on startup. The `EnvConfig` class reads the `.env` file and sets them as system properties, which Spring Boot can then access.

### 5. Security Notes

- Never commit `.env` files to version control
- Use IAM roles when possible instead of access keys
- Consider using AWS Secrets Manager for production environments
- The `.env` file is already added to `.gitignore`

## Running the Application

```bash
mvn spring-boot:run
```

## Configuration

The application uses environment variables for sensitive configuration. See `src/main/resources/globalProperties/consolidated.properties` for the complete configuration structure.
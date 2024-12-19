FROM amd64/openjdk:21-jdk-slim

WORKDIR /app
RUN apt-get update && apt-get install -y curl

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    default-mysql-client \
    netcat-openbsd \
    ca-certificates && \
    rm -rf /var/lib/apt/lists/*


COPY target/TaskManagement-0.0.1-SNAPSHOT.jar app.jar
# Copy the wait-for-it.sh script into the container
COPY wait-for-it.sh /app/wait-for-it.sh

# Make the script executable
RUN chmod +x /app/wait-for-it.sh

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]

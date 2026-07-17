#!/usr/bin/env bash
# Environment Verification Harness

echo "Checking Java version (must be 21)..."
java -version 2>&1 | grep -E "version \"21"
if [ $? -ne 0 ]; then
  echo "ERROR: Java 21 is required but not active. Please set JAVA_HOME correctly."
  exit 1
fi

echo "Checking for local.properties..."
if [ ! -f "local.properties" ]; then
  echo "ERROR: local.properties not found. Please create it and specify sdk.dir."
  exit 1
fi

echo "Verifying Gradle wrapper..."
./gradlew --version > /dev/null
if [ $? -ne 0 ]; then
  echo "ERROR: Gradle wrapper failed to execute."
  exit 1
fi

echo "Running baseline compilation..."
./gradlew clean assembleDebug -q --build-cache
if [ $? -ne 0 ]; then
  echo "ERROR: Baseline compilation failed. Please fix build errors before proceeding."
  exit 1
fi

echo "Environment verification passed."
exit 0

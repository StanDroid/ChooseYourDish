@echo off
echo Checking Java version (must be 21)...
java -version 2>&1 | findstr /i "version \"21" >nul
if %errorlevel% neq 0 (
    echo ERROR: Java 21 is required but not active. Please set JAVA_HOME correctly.
    exit /b 1
)

echo Checking for local.properties...
if not exist local.properties (
    echo ERROR: local.properties not found. Please create it and specify sdk.dir.
    exit /b 1
)

echo Verifying Gradle wrapper...
call gradlew.bat --version >nul
if %errorlevel% neq 0 (
    echo ERROR: Gradle wrapper failed to execute.
    exit /b 1
)

echo Running baseline compilation...
call gradlew.bat clean assembleDebug -q --build-cache
if %errorlevel% neq 0 (
    echo ERROR: Baseline compilation failed. Please fix build errors before proceeding.
    exit /b 1
)

echo Environment verification passed.
exit /b 0

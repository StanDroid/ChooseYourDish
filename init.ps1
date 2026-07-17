# Environment Verification Harness (PowerShell)

Write-Host "Checking Java version (must be 21)..."
$javaVersion = (java -version 2>&1) -join " "
if ($javaVersion -notmatch 'version "21') {
    Write-Error "ERROR: Java 21 is required but not active. Please set JAVA_HOME correctly."
    exit 1
}

Write-Host "Checking for local.properties..."
if (-not (Test-Path "local.properties")) {
    Write-Error "ERROR: local.properties not found. Please create it and specify sdk.dir."
    exit 1
}

Write-Host "Verifying Gradle wrapper..."
& .\gradlew.bat --version | Out-Null
if ($LASTEXITCODE -ne 0) {
    Write-Error "ERROR: Gradle wrapper failed to execute."
    exit 1
}

Write-Host "Running baseline compilation..."
& .\gradlew.bat clean assembleDebug -q --build-cache
if ($LASTEXITCODE -ne 0) {
    Write-Error "ERROR: Baseline compilation failed. Please fix build errors before proceeding."
    exit 1
}

Write-Host "Environment verification passed."
exit 0

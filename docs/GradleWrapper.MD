# Install Gradle

If Gradle is not installed on your laptop and you want to generate the Gradle wrapper, you can use the following method without requiring a pre-installed Gradle.

### Step 1: Use the Gradle Wrapper from a Remote Gradle Distribution

Even without Gradle installed locally, you can still generate the Gradle wrapper using a one-liner command that downloads and runs a specific version of Gradle directly.

#### For Unix/Linux/macOS:
Run this command in the terminal:
```bash
curl -s https://get.sdkman.io | bash && source "$HOME/.sdkman/bin/sdkman-init.sh" && sdk install gradle
```

#### For Windows:
Run this command in PowerShell:
```bash
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
```

# Setup Gradle Wrapper
To create a Gradle wrapper for an existing project, follow these steps:

### Step 1: Open your project directory
Navigate to your project’s root directory where the `build.gradle` file is located.

### Step 2: Generate the Gradle wrapper
Run the following command in your terminal or command prompt:

```bash
gradle wrapper
```

This will generate the necessary files for the Gradle wrapper in your project.

### Step 3: Verify the generated files
Once the command runs successfully, it will generate the following files and directories in your project:

- `gradle/wrapper/gradle-wrapper.jar`
- `gradle/wrapper/gradle-wrapper.properties`
- `gradlew` (Unix shell script)
- `gradlew.bat` (Windows batch script)

### Step 4: Use the Gradle wrapper
You can now use the Gradle wrapper to execute Gradle tasks without needing a pre-installed Gradle on the machine. Use the following commands:

- For Unix/Linux/macOS:
  ```bash
  ./gradlew build
  ```

- For Windows:
  ```cmd
  gradlew.bat build
  ```

This ensures the project will always use the specified Gradle version, even if Gradle is not installed locally.

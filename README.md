# MCP Server

## Build the Project

Run the following command to build the project:

```bash
./gradlew clean build
```

## Start MCP Server

### Step 1: Navigate to build directory

Navigate to the build directory where JARs exist:

```bash
cd build/libs
```

### Step 2: Start MCP Inspector

Start the MCP Inspector:

```bash
npx @modelcontextprotocol/inspector
```

## Start MCP Server and Connect

Enter the following command and arguments on MCP Inspector to start the MCP server and connect:

```bash
java -jar mycpserver-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```
**Command:**
java

**Arguments:**

- "-jar"
- "mycpserver-0.0.1-SNAPSHOT.jar" - Specifies the JAR file to run
- "--spring.profiles.active=local" - Activates the local Spring profile

Note: All arguments must be enclosed withing double quotes and seperated by spaces

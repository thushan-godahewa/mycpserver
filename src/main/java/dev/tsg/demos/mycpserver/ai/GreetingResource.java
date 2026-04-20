package dev.tsg.demos.mycpserver.ai;

import java.util.List;
import java.util.Map;

import org.springframework.ai.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.spec.McpSchema.ReadResourceResult;
import io.modelcontextprotocol.spec.McpSchema.TextResourceContents;

@Component
public class GreetingResource {

    @McpResource(
        uri = "demo://greeting/{name}",
        name = "greeting",
        description = "Provides a personalized greeting message",
        mimeType = "text/plain"
    )
    public ReadResourceResult getGreeting(String name) {
        try {
            int number = Integer.parseInt(name);
            String successString = "Hello, " + number + ", welcome to MyCPServer!";
            Map<String, Object> metadata = Map.of("inputType", "integer"
                , "inputValue", number
                , "greetingLength", successString.length()
                , "timestamp", System.currentTimeMillis()
                , "success", true);
            return new ReadResourceResult(List.of(
                new TextResourceContents("demo://greeting/{name}" + name, "text/plain", successString, metadata)
            ));
        } catch (NumberFormatException e) {
            String failedString = "Number format error: '" + name + "' is not a valid integer.";
            Map<String, Object> metadata = Map.of("inputType", "string", "inputValue", name, "success", false);
            return new ReadResourceResult(List.of(
                new TextResourceContents("demo://greeting/{name}" + name, "text/plain", failedString, metadata)
            ));
        }
    }
}

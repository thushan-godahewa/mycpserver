package dev.tsg.demos.mycpserver.ai.resources;

import java.util.List;

import org.springframework.ai.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.spec.McpSchema.ReadResourceResult;
import io.modelcontextprotocol.spec.McpSchema.TextResourceContents;;

@Component
public class GreetingsResource {

    /**
     * The function `getGreeting` returns a greeting message with the provided name
     * for a service.
     * 
     * @param name The `name` parameter in the code snippet represents the name of
     *             the person to whom
     *             the greeting message will be addressed. It is used to personalize
     *             the greeting message by
     *             including the provided name in the message.
     * @return A `ReadResourceResult` object is being returned, which contains a
     *         list of
     *         `TextResourceContents`. Each `TextResourceContents` object represents
     *         a text resource with the
     *         specified URI, media type ("text/plain"), and content ("Hello,
     *         {name}! Welcome to our service.
     *         How can I assist you today?").
     */
    @McpResource(uri = "greetings:hello/{name}", name = "greeting", description = "Provides a greeting message")
    public ReadResourceResult getGreeting(String name) {
        return new ReadResourceResult(
                List.of(new TextResourceContents("greetings:hello/" + name,
                        "text/plain",
                        "Hello, " + name + "! Welcome to our service. How can I assist you today?")));
    }
}

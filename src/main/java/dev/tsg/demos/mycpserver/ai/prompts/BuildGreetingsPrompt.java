package dev.tsg.demos.mycpserver.ai.prompts;

import java.util.List;

import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.GetPromptResult;

@Component
public class BuildGreetingsPrompt {

        /**
         * The function "buildGreetingsPrompt" generates a greeting message for the user
         * based on various
         * input parameters.
         * 
         * @param recipientName Recipient's name to whom the greeting message is
         *                      addressed.
         * @param eventName     The `eventName` parameter is a String representing the
         *                      name of the event for
         *                      which you want to build a greeting message. It could be
         *                      something like "Birthday Party",
         *                      "Wedding Celebration", "Company Anniversary", etc.
         * @param eventType     The `eventType` parameter in the `buildGreetingsPrompt`
         *                      method represents the
         *                      type or category of the event for which the greeting
         *                      message is being generated. It could be a
         *                      birthday party, wedding, anniversary, graduation, etc.
         *                      This information helps tailor the
         *                      greeting message to suit the specific occasion.
         * @param hostName      The `hostName` parameter in the `buildGreetingsPrompt`
         *                      method refers to the name
         *                      of the person hosting the event or occasion for which
         *                      the greeting message is being generated.
         *                      This could be the name of the individual or organization
         *                      hosting the event.
         * @param eventDate     The `eventDate` parameter in the `buildGreetingsPrompt`
         *                      method represents the
         *                      date of the event for which you are building a greeting
         *                      message. It is of type `LocalDate`,
         *                      which is a date without a time zone in the ISO-8601
         *                      calendar system. You can use this parameter
         *                      to
         * @param eventLocation The `eventLocation` parameter in the
         *                      `buildGreetingsPrompt` method refers
         *                      to the location where the event is taking place. This
         *                      could be a physical address, a venue name,
         *                      or any other relevant information about the event's
         *                      location.
         * @param tone          The `tone` parameter in the `buildGreetingsPrompt`
         *                      method is used to specify the
         *                      tone or mood of the greeting message being built. It
         *                      could be formal, casual, friendly,
         *                      professional, celebratory, etc., depending on the
         *                      context of the event and the relationship
         *                      between the sender and the recipient
         * @param customNotes   The `customNotes` parameter in the
         *                      `buildGreetingsPrompt` method is used to
         *                      include any additional personalized messages or notes
         *                      that you want to add to the greeting
         *                      message for the user. This allows you to customize the
         *                      greeting further based on specific
         *                      details or preferences related to the event or
         *                      recipient.
         * @return A GetPromptResult object is being returned, which contains a prompt
         *         message for building
         *         a greeting message for the user.
         */
        @McpPrompt(name = "buildGreetingsPrompt", description = "Build a greeting message for the user.")
        public GetPromptResult buildPromString(String recipientName,
                        @McpArg(name = "Event Name", description = "Name of the event", required = true) String eventName,
                        @McpArg(name = "Event Type", description = "Event Type. Example: Corporate Conference", required = true) String eventType,
                        @McpArg(name = "Host Name", description = "Name of the event host", required = true) String hostName,
                        @McpArg(name = "Event Date", description = "Event Date in format yyyy-MM-dd", required = true) String eventDate,
                        @McpArg(name = "Event Location", description = "Location of the event", required = true) String eventLocation,
                        @McpArg(name = "Tone", description = "Tone to write the greeting. Example: Professional and welcoming", required = true) String tone,
                        @McpArg(name = "Custom Notes", description = "Custom notes. Example: Focus on innovation and collaboration", required = false) String customNotes) {
                String prompt = build(recipientName, eventName, eventType, hostName, eventDate, eventLocation, tone,
                                customNotes);
                return new GetPromptResult("Build Greetings Prompt",
                                List.of(new McpSchema.PromptMessage(McpSchema.Role.USER,
                                                new McpSchema.TextContent(prompt))));
        }

        private String build(String recipientName, String eventName, String eventType, String hostName,
                        String eventDate,
                        String eventLocation, String tone, String customNotes) {
                return """
                                You are a professional event planner and communications expert.

                                Write a polished and engaging greeting message for an event.

                                Details:
                                - Recipient Name: %s
                                - Event Name: %s
                                - Event Type: %s
                                - Host/Organization Name: %s
                                - Date (optional): %s
                                - Location (optional): %s
                                - Tone: %s
                                - Special Notes (optional): %s

                                Instructions:
                                - Address the recipient by name.
                                - Begin with a warm and appropriate greeting.
                                - Mention the event and its significance.
                                - Reflect the specified tone consistently.
                                - Keep the message concise but meaningful (100–150 words).
                                - If relevant, include appreciation for attendance or excitement for their presence.
                                - Avoid clichés and overly generic phrases.

                                Output:
                                Return only the final greeting message, ready to be sent.

                                """.formatted(recipientName, eventName, eventType, hostName,
                                eventDate,
                                eventLocation,
                                tone, customNotes != null ? customNotes : "N/A");
        }
}

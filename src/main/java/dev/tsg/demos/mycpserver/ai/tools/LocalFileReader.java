package dev.tsg.demos.mycpserver.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.spec.McpSchema.CallToolResult;

@Component
public class LocalFileReader {

    /**
     * The `readFile` function reads the content of a local file specified by its
     * path and returns the
     * content or an error message.
     * 
     * @param filePath The `filePath` parameter in the `readFile` method is a string
     *                 that represents
     *                 the path to a local file that you want to read the content
     *                 of.
     * @return The method `readFile` returns a `CallToolResult` object which
     *         contains information about
     *         whether an error occurred during the file reading process and the
     *         content of the file as text.
     */
    @Tool(name = "read_local_file", description = "Reads the content of a local file given its path.")
    public CallToolResult readFile(@ToolParam(description = "Path of the local file to read") String filePath) {
        String contents = null;
        boolean isError = false;
        try {
            contents = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
        } catch (java.io.IOException e) {
            contents = "Error reading file: " + e.getMessage();
            isError = true;
        }
        return CallToolResult.builder()
                .isError(isError)
                .addTextContent(contents)
                .build();
    }
}

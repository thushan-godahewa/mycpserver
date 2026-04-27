package dev.tsg.demos.mycpserver.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.spec.McpSchema.CallToolResult;

@Component
public class LocalFileListingTool {

    /**
     * This Java function lists the files in a local directory specified by the
     * given path.
     * 
     * @param directoryPath The `directoryPath` parameter in the `list_local_files`
     *                      tool represents the
     *                      path of the local directory for which you want to list
     *                      the files. This method uses Java NIO's
     *                      `Files.list` to list the files in the specified
     *                      directory. If an error occurs during the
     *                      process, it catches
     * @return The method `listFiles` returns a `CallToolResult` object which
     *         contains information
     *         about the result of listing the files in a local directory. The
     *         `CallToolResult` object includes
     *         a boolean flag indicating if there was an error, and the contents of
     *         the files listed in the
     *         directory or an error message if an exception occurred during the
     *         process.
     */
    @Tool(name = "list_local_files", description = "Lists the files in a local directory given its path.")
    public CallToolResult listFiles(
            @ToolParam(description = "Path of the local directory to list the files") String directoryPath) {
        String contents = null;
        boolean isError = false;
        StringBuilder result = new StringBuilder();
        try {
            java.nio.file.Files.list(java.nio.file.Paths.get(directoryPath))
                    .forEach(path -> result.append(path.getFileName().toString()).append("\n"));
            contents = result.toString();
        } catch (java.io.IOException e) {
            contents = "Error listing files: " + e.getMessage();
            isError = true;
        }
        return CallToolResult.builder()
                .isError(isError)
                .addTextContent(contents)
                .build();
    }
}

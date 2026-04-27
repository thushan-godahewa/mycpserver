package dev.tsg.demos.mycpserver;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.tsg.demos.mycpserver.ai.tools.LocalFileListingTool;
import dev.tsg.demos.mycpserver.ai.tools.LocalFileReader;

@SpringBootApplication
public class MycpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycpserverApplication.class, args);
	}

	/**
	 * This function registers tools by creating a ToolCallbackProvider using MethodToolCallbackProvider
	 * with specified tool objects.
	 * 
	 * @param localFileReader A component responsible for reading local files.
	 * @param localFileListingTool LocalFileListingTool is a tool used for listing files from a local
	 * directory. It is likely a component or service that provides functionality related to reading and
	 * listing files from a local file system.
	 * @return A `ToolCallbackProvider` object is being returned.
	 */
	@Bean
	public ToolCallbackProvider registerTools(LocalFileReader localFileReader, 
		LocalFileListingTool localFileListingTool) {
		return MethodToolCallbackProvider.builder()
				.toolObjects(localFileReader, localFileListingTool)
				.build();
	}

}

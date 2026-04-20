package dev.tsg.demos.mycpserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import dev.tsg.demos.mycpserver.model.GreetingRequest;

@SpringBootTest
@ContextConfiguration(classes = {GreetingRequest.class})
class MycpserverApplicationTests {

	@Test
	void contextLoads() {
	}

}

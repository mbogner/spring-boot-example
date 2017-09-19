package pm.mbo.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationTest {

    private static final String SPRING_STARTUP = "root of context hierarchy";

    @Test
    public void testMain() throws Exception {
        Application.main();
    }

}
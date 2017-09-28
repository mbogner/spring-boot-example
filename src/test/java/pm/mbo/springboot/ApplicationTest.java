package pm.mbo.springboot;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationTest {

    @Test
    public void testMain() throws Exception {
        Application.main();
    }

}
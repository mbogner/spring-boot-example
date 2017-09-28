package pm.mbo.springboot.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pm.mbo.springboot.dto.Example;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void index() throws Exception {
        base = new URL("http://localhost:" + port + IndexController.INDEX_PATH);

        final ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }

    @Test
    public void example() throws Exception {
        base = new URL("http://localhost:" + port + IndexController.EXAMPLE_PATH + "?name=Test");
        final Example expected = new Example(1L, "Hello, Test!");

        final ResponseEntity<Example> response = template.getForEntity(base.toString(), Example.class);
        assertThat(response.getBody()).isEqualTo(expected);
    }

}
package pm.mbo.springboot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pm.mbo.springboot.dto.Example;
import pm.mbo.springboot.model.Reading;
import pm.mbo.springboot.repository.ReadingRepository;

import java.util.concurrent.atomic.AtomicLong;

import static pm.mbo.springboot.Application.API_PATH;

@RestController
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    public static final String INDEX_PATH = API_PATH + "/";
    public static final String EXAMPLE_PATH = API_PATH + "/example";

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ReadingRepository readingRepository;

    @RequestMapping(value = INDEX_PATH, method = RequestMethod.GET)
    public String index() {
        final long count = readingRepository.count();
        LOG.debug("found {} entries", count);
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = EXAMPLE_PATH, method = RequestMethod.GET)
    public Example example(@RequestParam(value = "name", defaultValue = "World") final String name) {
        return new Example(counter.incrementAndGet(),
                String.format(template, name));
    }

}

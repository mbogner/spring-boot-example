package pm.mbo.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pm.mbo.springboot.model.Reading;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class ReadingRepositoryTest {

    @Autowired
    private ReadingRepository repo;

    @Test
    public void testSave() {
        final Reading reading = new Reading();
        reading.setValue(new BigDecimal("1"));

        repo.save(reading);
        assertThat(repo.count(), equalTo(1L));
    }

    @Test
    public void testFindAll() {
        final List<Reading> all = repo.findAll();
        assertThat(all.size(), equalTo(0));
    }

}
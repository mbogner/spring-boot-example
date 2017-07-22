package pm.mbo.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pm.mbo.springboot.model.Reading;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
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

}
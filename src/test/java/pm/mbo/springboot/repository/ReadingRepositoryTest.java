package pm.mbo.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pm.mbo.springboot.model.Reading;
import pm.mbo.springboot.model.ReadingType;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class ReadingRepositoryTest {

    @Autowired
    private ReadingTypeRepository readingTypeRepository;

    @Autowired
    private ReadingRepository readingRepository;

    @Test
    public void testSave() {
        final ReadingType type = ReadingTypeRepositoryTest.createValid("type2");
        readingTypeRepository.save(type);
        assertThat(type.getId(), notNullValue());

        final ReadingType dbType = readingTypeRepository.findOne(type.getId());

        final Reading reading = createValid(1.0, dbType);
        readingRepository.save(reading);
        assertThat(readingRepository.count(), equalTo(1L));
    }

    @Test
    public void testFindAll() {
        final List<Reading> all = readingRepository.findAll();
        assertThat(all.size(), equalTo(0));
    }

    public static Reading createValid(final double value, final ReadingType type) {
        final Reading reading = new Reading();
        reading.setValue(new BigDecimal(String.valueOf(value)));
        reading.setReadingType(type);
        return reading;
    }

}
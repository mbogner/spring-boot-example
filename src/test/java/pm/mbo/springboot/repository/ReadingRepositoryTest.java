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

import static org.assertj.core.api.Assertions.assertThat;

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
        ReadingType type = ReadingTypeRepositoryTest.createValid("type2");
        type = readingTypeRepository.save(type);
        assertThat(type.getId()).isNotNull();
        final Reading reading = createValid(1.0, type);
        readingRepository.save(reading);
        assertThat(readingRepository.count()).isEqualTo(1);
    }

    @Test
    public void testFindAll() {
        final List<Reading> all = readingRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    public static Reading createValid(final double value, final ReadingType type) {
        final Reading reading = new Reading();
        reading.setValue(new BigDecimal(String.valueOf(value)));
        reading.setReadingType(type);
        return reading;
    }

}
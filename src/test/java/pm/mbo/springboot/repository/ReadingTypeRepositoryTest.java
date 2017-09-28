package pm.mbo.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pm.mbo.springboot.model.ReadingType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class ReadingTypeRepositoryTest {

    @Autowired
    private ReadingTypeRepository repo;

    @Test
    public void testSave() {
        final ReadingType reading = createValid("type1");
        repo.save(reading);
        assertThat(reading).isNotNull();
        assertThat(reading.getId()).isNotNull();
        assertThat(repo.count()).isEqualTo(1);
    }

    @Test
    public void testFindAll() {
        final List<ReadingType> all = repo.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    public static ReadingType createValid(final String name) {
        final ReadingType readingType = new ReadingType();
        readingType.setName(name);
        return readingType;
    }

}
package pm.mbo.springboot.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pm.mbo.springboot.model.ReadingType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReadingTypeRepositoryTest {

    @Autowired
    private ReadingTypeRepository repo;

    @Test
    void testSave() {
        final ReadingType reading = createValid("type1");
        repo.save(reading);
        assertThat(reading).isNotNull();
        assertThat(reading.getId()).isNotNull();
        assertThat(repo.count()).isEqualTo(1);
    }

    @Test
    void testFindAll() {
        final List<ReadingType> all = repo.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    public static ReadingType createValid(final String name) {
        final ReadingType readingType = new ReadingType();
        readingType.setName(name);
        return readingType;
    }

}
package pm.mbo.springboot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.assertj.core.api.Assertions.assertThat;

public class SomeTests {

    @Rule
    public TestName name = new TestName();

    @Test
    public void someTest() {
        assertThat(1 + 1).isEqualTo(2);
        //fail("testing");
    }

}

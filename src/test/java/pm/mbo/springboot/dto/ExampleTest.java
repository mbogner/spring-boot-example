package pm.mbo.springboot.dto;

import org.junit.Test;
import pm.mbo.springboot.util.BeanTest;

public class ExampleTest extends BeanTest<Example> {

    @Override
    public Class<Example> getClassUnderTest() {
        return Example.class;
    }

    @Test
    public void testDefaultConstructor() {
        new Example();
    }
}
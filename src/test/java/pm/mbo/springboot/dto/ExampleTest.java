package pm.mbo.springboot.dto;

import pm.mbo.springboot.util.BeanTest;

public class ExampleTest extends BeanTest<Example> {

    @Override
    public Class<Example> getClassUnderTest() {
        return Example.class;
    }
}
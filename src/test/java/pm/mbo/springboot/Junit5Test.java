package pm.mbo.springboot;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Junit5Test {

    @SuppressWarnings("unused") // not recognized by intellij at the moment
    @BeforeEach
    private void setUp(final TestInfo testInfo, final TestReporter testReporter) {
        testReporter.publishEntry("method", testInfo.getTestMethod().get().getName());
        testReporter.publishEntry("displayName", testInfo.getDisplayName());
        testReporter.publishEntry("tags", StringUtils.join(testInfo.getTags(), ","));
    }

    @Tag("params")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void testWithStringParameter(String argument) {
        assertThat(argument).isNotNull();
    }

    @Tag("repeated")
    @RepeatedTest(10)
    void repeatedTest() {
    }


}

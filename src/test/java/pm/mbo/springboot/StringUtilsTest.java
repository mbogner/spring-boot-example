package pm.mbo.springboot;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringUtilsTest {

    @Test
    public void testStringUtilsCapitalize() {
        assertThat(StringUtils.capitalize("nameBla"), equalTo("NameBla"));
        assertThat(StringUtils.capitalize("name_bla"), equalTo("Name_bla"));
    }

}

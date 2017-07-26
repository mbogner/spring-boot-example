package pm.mbo.springboot;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {

    @Test
    public void testStringUtilsCapitalize() {
        assertThat(StringUtils.capitalize("nameBla"), equalTo("NameBla"));
        assertThat(StringUtils.capitalize("name_bla"), equalTo("Name_bla"));
    }

}

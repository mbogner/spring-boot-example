package pm.mbo.springboot.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public abstract class BeanTest<T> {

    /**
     * Every bean has to declare an empty public constructor.
     */
    @Test
    public void testNoArgsConstructor() throws Exception {
        final Constructor<T> constructor = getClassUnderTest().getDeclaredConstructor();
        assertThat(constructor, notNullValue());
        assertThat(Modifier.isPublic(constructor.getModifiers()), is(true));
    }

    /**
     * Every bean has to declare a toString method.
     */
    @Test
    public void testToString() throws Exception {
        final Method method = getClassUnderTest().getDeclaredMethod("toString");
        assertThat(Modifier.isPublic(method.getModifiers()), is(true));
        assertThat(method.getReturnType(), equalTo(String.class));
    }

    /**
     * Every bean has to declare an equals method.
     */
    @Test
    public void testEquals() throws Exception {
        final Method method = getClassUnderTest().getDeclaredMethod("equals", Object.class);
        assertThat(Modifier.isPublic(method.getModifiers()), is(true));
        assertThat(method.getReturnType(), equalTo(Boolean.TYPE));
    }

    /**
     * Every bean has to declare a hashCode method.
     */
    @Test
    public void testHashCode() throws Exception {
        final Method method = getClassUnderTest().getDeclaredMethod("hashCode");
        assertThat(Modifier.isPublic(method.getModifiers()), is(true));
        assertThat(method.getReturnType(), equalTo(Integer.TYPE));
    }

    @Test
    public void testGetterSetter() {
        checkBean(getClassUnderTest());
    }

    protected void checkBean(final Class<?> clazz) {
        Class<?> nextClass = checkClass(clazz);
        if (null != nextClass) {
            checkBean(nextClass);
        }
    }

    protected Class<?> checkClass(final Class<?> clazz) {
        for (final Field field : clazz.getDeclaredFields()) {
            final String fieldName = field.getName();
            final String fieldNameUc = StringUtils.capitalize(fieldName);
            boolean correctGetter = false;
            boolean correctSetter = false;

            for (final Method method : clazz.getDeclaredMethods()) {
                boolean common = false;
                if (method.getName().equals("get" + fieldNameUc)) { // getter
                    assertThat(method.getParameterCount(), equalTo(0));
                    assertThat(method.getReturnType(), equalTo(field.getType()));
                    correctGetter = true;
                } else if (method.getName().equals("set" + fieldNameUc)) { // setter
                    assertThat(method.getParameterCount(), equalTo(1));
                    assertThat(method.getParameterTypes()[0], equalTo(field.getType()));
                    assertThat(method.getReturnType(), equalTo(Void.TYPE));
                    correctSetter = true;
                } else {
                    continue;
                }
                // common
                assertThat(Modifier.isStatic(method.getModifiers()), is(false));
                assertThat(Modifier.isPublic(method.getModifiers()), is(true));
            }
            assertThat(correctGetter, is(true));
            assertThat(correctSetter, is(true));
        }
        if (clazz.getSuperclass() != Object.class) {
            return clazz.getSuperclass();
        } else {
            return null;
        }
    }

    public abstract Class<T> getClassUnderTest();

}
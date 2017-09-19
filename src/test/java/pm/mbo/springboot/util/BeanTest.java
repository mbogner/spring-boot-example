package pm.mbo.springboot.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class BeanTest<T> {

    /**
     * Every bean has to declare an empty public constructor.
     */
    @Test
    void testNoArgsConstructor() throws Exception {
        final Constructor<T> constructor = getClassUnderTest().getDeclaredConstructor();
        assertThat(constructor).isNotNull();
        assertThat(Modifier.isPublic(constructor.getModifiers())).isTrue();
    }

    /**
     * Every bean has to declare a toString method.
     */
    @Test
    void testToString() throws Exception {
        final Method method = getClassUnderTest().getDeclaredMethod("toString");
        assertThat(Modifier.isPublic(method.getModifiers())).isTrue();
        assertThat(method.getReturnType()).isEqualTo(String.class);
    }

    /**
     * Every bean has to declare an equals method.
     */
    @Test
    void testEquals() throws Exception {
        final Method method = getClassUnderTest().getDeclaredMethod("equals", Object.class);
        assertThat(Modifier.isPublic(method.getModifiers())).isTrue();
        assertThat(method.getReturnType()).isEqualTo(Boolean.TYPE);
    }

    /**
     * Every bean has to declare a hashCode method.
     */
    @Test
    void testHashCode() throws Exception {
        final Method method = getClassUnderTest().getDeclaredMethod("hashCode");
        assertThat(Modifier.isPublic(method.getModifiers())).isTrue();
        assertThat(method.getReturnType()).isEqualTo(Integer.TYPE);
    }

    @Test
    void testGetterSetter() {
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
            if (fieldName.startsWith("$")) { // exclude jacoco fields
                continue;
            }
            final String fieldNameUc = StringUtils.capitalize(fieldName);
            boolean correctGetter = false;
            boolean correctSetter = false;

            for (final Method method : clazz.getDeclaredMethods()) {
                boolean common = false;
                if (method.getName().equals("get" + fieldNameUc)) { // getter
                    assertThat(method.getParameterCount()).isEqualTo(0);
                    assertThat(method.getReturnType()).isEqualTo(field.getType());
                    correctGetter = true;
                } else if (method.getName().equals("set" + fieldNameUc)) { // setter
                    assertThat(method.getParameterCount()).isEqualTo(1);
                    assertThat(method.getParameterTypes()[0]).isEqualTo(field.getType());
                    assertThat(method.getReturnType()).isEqualTo(Void.TYPE);
                    correctSetter = true;
                } else {
                    continue;
                }
                // common
                assertThat(Modifier.isStatic(method.getModifiers())).isFalse();
                assertThat(Modifier.isPublic(method.getModifiers())).isTrue();
            }
            assertThat(correctGetter).isTrue();
            assertThat(correctSetter).isTrue();
            callGetterSetter(clazz, field, fieldNameUc);
        }
        if (clazz.getSuperclass() != Object.class) {
            return clazz.getSuperclass();
        } else {
            return null;
        }
    }

    protected void callGetterSetter(final Class<?> clazz, final Field field, final String fieldNameUc) {
        try {
            final Object obj = clazz.newInstance();
            final Method getter = clazz.getDeclaredMethod(String.format("get%s", fieldNameUc));
            assertThat(getter).isNotNull();
            final Method setter = clazz.getDeclaredMethod(String.format("set%s", fieldNameUc), getter.getReturnType());
            assertThat(setter).isNotNull();

            System.out.println("invoke " + getter.getName());
            getter.invoke(obj);

            System.out.println("invoke " + setter.getName());
            setter.invoke(obj, new Object[]{null}); // not working for primitives
        } catch (final InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            fail(e);
        }
    }

    public abstract Class<T> getClassUnderTest();

}
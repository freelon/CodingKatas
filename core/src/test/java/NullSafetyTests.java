import org.junit.jupiter.api.Test;

import static de.torks.common.NullSafety.getOrDefault;
import static de.torks.common.NullSafety.getOrNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NullSafetyTests {

    @Test
    void testNestedValueExists() {
        A a = new A(new B("hello"));
        assertEquals("hello", getOrNull(() -> a.b().s()));
    }

    @Test
    void testNullOnPath() {
        A a = new A(null);
        assertNull(getOrNull(() -> a.b().s()));
    }

    @Test
    void testPrimitive() {
        C c = new C(1);
        assertEquals(1, getOrNull(c::i));
    }

    @Test
    void testNValueInsteadOfDefault() {
        A a = new A(new B("hello"));
        assertEquals("hello", getOrDefault(() -> a.b().s(), "default"));
    }

    @Test
    void testDefault() {
        A a = new A(null);
        assertEquals("default", getOrDefault(() -> a.b().s(), "default"));
    }

    record A(B b) {
    }

    record B(String s) {
    }

    record C(int i) {
    }
}

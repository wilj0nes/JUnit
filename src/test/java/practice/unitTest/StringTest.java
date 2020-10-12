package practice.unitTest;

//import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

public class StringTest {

    @BeforeAll // methods with this annotation must be static
    static public void beforeAll() {
        System.out.println("Calling beforeAll() once");
    }

    @BeforeEach // called @Before in jUnit 4
    public void beforeEach(TestInfo info) { // TestInfo is not part of jUnit 4
        System.out.println("Calling... beforeEach() before: " + info.getDisplayName());

    }

    @AfterEach // called @After in jUnit 4
    public void afterEach(TestInfo info) {
        System.out.println("Calling... afterEach() after: " + info.getDisplayName());
    }

    @Test
    public void test() {
        int actualLength = "ABCD".length();
        int expectedLength = 4;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void contains_basic() {
        assertFalse("abcdefgh".contains("ijk"));
    }

    @Test
    public void split_basic() {
        String str= "abc def ghi";
        String[] actualResult = str.split(" " );
        String[] expectedResult = new String[]{"abc", "def", "ghi"};

        // expected should always be the first parameter
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void length_exception() {
        String str = null;
        assertThrows(NullPointerException.class,
                () -> {
                    int actualLength = str.length();
                }
        );
    }

    @Test
    public void length_greater_than_zero() {
        assertTrue("abcd".length() > 0);
        assertTrue("abc".length() > 0);
        assertTrue("a".length() > 0);
        assertTrue("def".length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings= {"abcd", "abc", "a", "def"}) // this will execute the test several times with these values
    public void length_greater_than_zero_parameterized_test(String str) {
        assertTrue(str.length() > 0);
    }

    @ParameterizedTest
    @CsvSource(value= {"abcd, ABCD", "abc, ABC", "def, DEF", "'', ''"}) // <-- String word, String capitalizedWord
    public void upperCase(String word, String capitalizedWord) {
        assertEquals(capitalizedWord, word.toUpperCase());
    }

}

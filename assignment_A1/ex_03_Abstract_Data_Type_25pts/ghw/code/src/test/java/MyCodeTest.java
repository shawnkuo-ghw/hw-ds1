import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MyCodeTest {

    MyCode mycode;

    @BeforeEach
    void initMycode() {
        mycode = new MyCode();
    }

    @Test
    void retTrueFalseTest1() {
        assertTrue(mycode.retTrue());
        assertFalse(mycode.retFalse());
    }

    @Test
    void retTrueFalseTest2() {
        assertTrue(mycode.retTrue());
        assertFalse(mycode.retFalse());
    }

}
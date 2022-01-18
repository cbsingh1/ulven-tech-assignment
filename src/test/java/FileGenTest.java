import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileGenTest {

    FileGenApp fileGenApp;

    @BeforeEach
    void setup() {
        fileGenApp = new FileGenApp();
    }

    @Test
    public void testFileExists() {
        File file = new File("output.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testInvalidInputException() {
        assertThrows(IllegalArgumentException.class, () -> fileGenApp.generateFile(0));
    }

    @Test
    public void testInvalidInputException2() {

        assertThrows(IllegalArgumentException.class,
                () -> fileGenApp.generateFile(230));
    }

    @Test
    public void testInvalidInputMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                                    () -> fileGenApp.generateFile(0));
        assertEquals("Invalid Line count", exception.getMessage());
    }
}



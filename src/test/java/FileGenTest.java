import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileGenTest {

    FileGenApp fileGenApp;

    @BeforeEach
    void setup() {
        fileGenApp = new FileGenApp();
    }

    @Test
    @DisplayName("File should be generated for succesful flow")
    public void testFileExists() {
        fileGenApp.generateFile(1);
        File file = new File("output.txt");
        assertTrue(file.exists());
    }

    @Test
    public void countLines() throws IOException {
        fileGenApp.generateFile(1);
        assertEquals(1, Files.lines(Paths.get("output.txt"), Charset.defaultCharset()).count());
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



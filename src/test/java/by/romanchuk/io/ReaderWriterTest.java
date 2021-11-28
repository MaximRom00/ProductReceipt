package by.romanchuk.io;


import org.junit.*;
import java.io.*;


import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Files;


import static org.assertj.core.api.Assertions.assertThat;

public class ReaderWriterTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void shouldWriteSomeTextToFileAndCheckIt() throws IOException {
        String testString = "test";
        File temporaryFile = temporaryFolder.newFile("testfile.txt");

        try (FileWriter writer = new FileWriter(temporaryFile)) {
            writer.write(testString);
            writer.flush();
            assertThat(temporaryFile)
                    .exists()
                    .hasContent("test")
                    .hasExtension("txt");
        }
    }

    @Test
    public void shouldReturnArgumentsFromText() throws IOException {
        String[] expected = new String[]{"3-2 2-5 5-5 card-1234"};

        File file = new File("src/main/resources/arguments.txt");

        String [] getArgs;

        if (file.exists()){
             getArgs = Files.readAllLines(file.toPath()).toArray(new String[0]);
        }
        else {
             throw new IllegalStateException("Filed not found - " + file.getAbsolutePath());
        }

        assertThat(expected).isEqualTo(getArgs);
    }
}
package by.romanchuk.io;

import by.romanchuk.entity.CashBox;
import by.romanchuk.exception.FileIllegalStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

public class ReaderWriter {
    private static final Logger log = LoggerFactory.getLogger(ReaderWriter.class);

    /**
     * Writes the final receipt to a file(receipt.txt).
     * The file is in resources folder.
     *
     * @param cashBox
     */
    public static void writer(CashBox cashBox)  {
        File file = new File("src/main/resources/receipt.txt");

        try(PrintWriter writer = new PrintWriter(file)){
            writer.println(cashBox);
            writer.flush();
        }
        catch (FileNotFoundException ex) {
            log.error("Exception {}: ", ex.getMessage());
        }
    }


    /**
     * Get args from txt file and return in main class for parsing.
     *
     * @param file
     * @return {@link String[]}
     */
    public static String[] reader(File file){
        String[] filesArgs;
        try {
            filesArgs = Files.readAllLines(file.toPath()).toArray(new String[0]);
        }
        catch (IOException ex) {
            log.error("Exception {}: ", ex.getMessage());
            throw new FileIllegalStateException(ex);
        }
        return filesArgs[0].split(" ");
    }
}

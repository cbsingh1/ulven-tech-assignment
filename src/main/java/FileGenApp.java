import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FileGenApp {

    private final int MIN_LINE_COUNT =1;
    private final int MAX_LINE_COUNT =229;
    private final int STRING_SIZE = 100;
    private final int DATE_STRING_SIZE = 17;

    public void generateFile(int lines) {
        validateLineCount(lines);

        //String fileName = "file_" + getCurrentTime() + ".txt";
        String fileName = "output.txt";

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {

            writer.write(generateString(STRING_SIZE));

            for (int i = 0; i <lines-1 ; i++) {
                writer.write(System.getProperty( "line.separator" ));
                writer.write(generateString(STRING_SIZE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateString(int strLength) {


        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;

        StringBuilder sb = new StringBuilder(strLength);

        //Generating Unique String with = 100 - 17 = 83 Chars.
        // Here, 17 chars for current timestamp(yyyyMMddHHmmssSSS) added to each string.

        for (int i = 0; i < strLength - DATE_STRING_SIZE ; i++) {
            int index  = (int)(alphanum.length()  * Math.random());
            sb.append(alphanum.charAt(index));
        }

        //Adding timestamp to end of string for uniqueness
        sb.append(getCurrentTime());

        return sb.toString();
    }

    private void validateLineCount(int lines) {

        if (lines< MIN_LINE_COUNT || lines > MAX_LINE_COUNT) {
            throw new IllegalArgumentException("Invalid Line count");
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}

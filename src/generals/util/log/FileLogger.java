package generals.util.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * The file logger logs the message into the file using a PrintWriter
 *
 * @author Yidi Chen
 * @date 2021-12-14
 */
public class FileLogger implements Logger {

    /**
     * The default file logger
     */
    public static final FileLogger DEFAULT_FILE_LOGGER = new FileLogger("log/log.txt");

    /**
     * The path of the file logging into
     */
    private String strFilePath;

    /**
     * Create the logger by the file path
     *
     * @param strFilePath the file path
     */
    public FileLogger(String strFilePath) {
        this.strFilePath = strFilePath;
    }

    /**
     * Log into the file
     *
     * @param level      level
     * @param strMessage message
     */
    @Override
    public void log(LogLevel level, String strMessage) {
        try {
            // open the writer
            PrintWriter writer = new PrintWriter(new FileWriter(strFilePath, StandardCharsets.UTF_8, true));
            // write into
            writer.println(Logger.getLogMessage(level, strMessage));
            // close
            writer.close();
        } catch (IOException ignored) {
        }
    }
}

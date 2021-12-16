package generals.util.log;

import java.util.Date;

/**
 * This is an interface for a logger.
 * A logger should be able to output the message from the Loggable objects
 *
 * @author Yidi Chen
 * @date 2021-12-14
 */
public interface Logger {

    /**
     * Output message with log level
     *
     * @param level      level
     * @param strMessage message
     */
    void log(LogLevel level, String strMessage);

    /**
     * The string format for the logger output
     */
    String STR_LOG_FORMAT = "%s [%s] %s";

    /**
     * Get the output message from the log level and the message
     *
     * @param level      the log level
     * @param strMessage the log message
     * @return the output message
     */
    static String getLogMessage(LogLevel level, String strMessage) {
        return String.format(STR_LOG_FORMAT, new Date(), level, strMessage);
    }
}

package generals.util.log;

/**
 * A loggable object is something which is able to log by calling methods
 *
 * @author Yidi Chen
 * @date 2021-12-14
 */
public interface Loggable {

    /**
     * The default loggers
     */
    Logger[] DEFAULT_LOGGERS = new Logger[]{StdoutLogger.DEFAULT_STDOUT_LOGGER, FileLogger.DEFAULT_FILE_LOGGER};

    /**
     * Get the loggers working for this loggable object
     *
     * @return the loggers
     */
    default Logger[] getLoggers() {
        return DEFAULT_LOGGERS;
    }

    /**
     * Log a message with level
     *
     * @param level      the level
     * @param strMessage the message
     */
    default void log(LogLevel level, String strMessage) {
        Logger[] loggers = getLoggers();
        for (int intCnt = 0; intCnt < loggers.length; intCnt++) {
            loggers[intCnt].log(level, strMessage);
        }
    }

    /**
     * Log message with level INFO
     *
     * @param strMessage the message
     */
    default void info(String strMessage) {
        log(LogLevel.INFO, strMessage);
    }

    /**
     * Log message with level WARN
     *
     * @param strMessage the message
     */
    default void warn(String strMessage) {
        log(LogLevel.WARN, strMessage);
    }

    /**
     * Log message with level ERROR
     *
     * @param strMessage the message
     */
    default void error(String strMessage) {
        log(LogLevel.ERROR, strMessage);
    }

    /**
     * Log message with level FATAL
     *
     * @param strMessage the message
     */
    default void fatal(String strMessage) {
        log(LogLevel.FATAL, strMessage);
    }
}

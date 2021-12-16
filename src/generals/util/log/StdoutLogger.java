package generals.util.log;

/**
 * This is an enum class for logging levels. The log levels should be rated in:
 * INFO < WARN < ERROR < FATAL
 *
 * @author Yidi Chen
 * @date 2021-12-14
 */
public class StdoutLogger implements Logger {

    /**
     * The default Stdout logger
     */
    public static final StdoutLogger DEFAULT_STDOUT_LOGGER = new StdoutLogger();

    /**
     * Construct a stdout logger
     */
    public StdoutLogger() {

    }

    /**
     * Log the message onto the console
     *
     * @param level      level
     * @param strMessage message
     */
    @Override
    public void log(LogLevel level, String strMessage) {
        System.out.println(Logger.getLogMessage(level, strMessage));
    }
}

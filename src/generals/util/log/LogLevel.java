package generals.util.log;

/**
 * This is an enum class for logging levels. The log levels should be rated in:
 * INFO < WARN < ERROR < FATAL
 *
 * @author Yidi Chen
 * @date 2021-12-14
 */
public enum LogLevel {
    /**
     * Info log level is a log level printing the basic information.
     */
    INFO,
    /**
     * Warn log level is a log level printing some warn from the program.
     * The warm might be dangerous but should not cause error
     */
    WARN,
    /**
     * Error log level is a log level printing errors which is dangerous but would not kill the program
     */
    ERROR,
    /**
     * Fatal log level is a log level used for printing the errors will kill program
     */
    FATAL
}

package logger;

/**
 * This interface is used as a basis for the logging classes.
 */
public interface ILogger {
    /**
     *  This function outputs the string message.
     * @param message The initial message
     */
    void write(String message);

    /**
     *  This function takes a number and loggs it to the stream
     * @param number The number
     */
    void write(long number);

    /**
     *  This function takes a double and loggs it to the stream.
     * @param number The number to write to the stream.
     */
    void write(double number);

    /**
     * This functions converts time to the specified time unit
     * @param time  The time in nanoseconds.
     * @param unit A time unit to convert to.
     */
    void writeTime(long time, TimeUnit unit);
}

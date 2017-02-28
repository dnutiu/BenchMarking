package logger;

/**
 * This interface is used as a basis for the logging classes.
 */
public interface ILogger {
    /**
     *  This function outputs the string message.
     * @param message The initial message
     */
    public void write(String message);

    /**
     *  This function takes a number and loggs it to the stream
     * @param number The number
     */
    public void write(long number);

    /**
     *  This function takes a double and loggs it to the stream.
     * @param number
     */
    public void write(double number);


    public void writeTime(long number, String message);
    // TODO : WriteTime /w time unit
}

package logger;

/**
 * The basic console logger class.
 */
public class ConsoleLogger implements ILogger {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void write(long number) {
        System.out.println(Long.toString(number));
    }

    @Override
    public void write(double number) {
        System.out.println(Double.toString(number));
    }

    @Override
    public void writeTime(long time, TimeUnit unit) {
        System.out.println("Total execution time: " + TimeUnit.converNanosecondTo(time, unit));
    }
}

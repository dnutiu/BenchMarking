package logger;

import org.jetbrains.annotations.Contract;

/**
 * The TimeUnit enum provides an easy method of converting between time units.
 */
public enum TimeUnit {
    NANOSECOND(1),
    MICROSECOND(1000),
    MILISECOND(1000000),
    SECOND(1000000000);

    private long precision;

    /**
     * Constructs a TimeUnit object with the desired precision.
     * If we want to convert nanoseconds in microseconds then we must select a precision of
     * 1000 because nanoseconds / 1000 = microseconds.
     * @param precision The precision will be as divisor for the time.
     */
    TimeUnit(long precision) {
        this.precision = precision;
    }

    /**
     * @return The precision for the time unit
     */
    public long getPrecision() {
        return this.precision;
    }


    /**
     * Converts between time units.
     * @param time Takes the time.
     * @param unit Takes the precision.
     * @return The quotient of time / time unit.
     */
    @Contract(pure = true)
    public long converNanosecondTo(long time, TimeUnit unit) {
        return (time / unit.precision);
    }
}

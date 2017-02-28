package logger;

import org.jetbrains.annotations.Contract;

/**
 * Created by denis on 28/02/17.
 */
public enum TimeUnit {
    NANOSECOND(1),
    MICROSECOND(1000),
    MILISECOND(1000000),
    SECOND(1000000000);

    private long precission;

    TimeUnit(long precission) {
        this.precission = precission;
    }

    public long getPrecission() {
        return this.precission;
    }

    @Contract(pure = true)
    public long converNanosecondTo(long time, TimeUnit unit) {
        return (time / unit.precission);
    }
}

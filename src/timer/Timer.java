package timer;

/**
 * This is a basic timer.
 */
public class Timer implements ITimer{
    private long startTime;
    private long endTime;
    private long timePassed;
    private long totalTime;

    @Override
    public void start() {
        timePassed = 0;
        startTime = System.nanoTime();
    }

    @Override
    public long stop() {
        endTime = System.nanoTime();
        timePassed += endTime - startTime;
        totalTime = timePassed;
        return totalTime;
    }

    @Override
    public long pause() {
        endTime = System.nanoTime();
        timePassed += endTime - startTime;
        return timePassed;
    }

    @Override
    public void resume() {
        startTime = System.nanoTime();
    }

    /**
     * Function used to get the time that has passed.
     * @return Returns the total time that has passed.
     */
    public long getTimePassed() {
        return this.timePassed;
    }
}

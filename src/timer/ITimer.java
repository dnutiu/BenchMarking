package timer;

/**
 * This is the timer interface.
 */
public interface ITimer {
    /**
     * This function starts the timer.
     */
    public void start();

    /**
     * This function stops the timer.
     * @return The passed time.
     */
    public long stop();

    /**
     * This function pauses the timer.
     * @return The passed time.
     */
    public long pause();

    /**
     * This function resumes the time.
     */
    public void resume();
}

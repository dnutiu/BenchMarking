package timer;

/**
 * The ITimer interface contains the basic functions that need to be used when implementing a timer.
 */
public interface ITimer {
    /**
     * This function starts the timer.
     */
    void start();

    /**
     * This function stops the timer.
     * @return The passed time.
     */
    long stop();

    /**
     * This function pauses the timer.
     * @return The passed time.
     */
    long pause();

    /**
     * This function resumes the time.
     */
    void resume();
}

package benchmark;

/**
 * Created by denis on 28/02/17.
 */
public interface IBenchmark {
    public void initialized(long see); // TODO: SEE neds clarification
    public void run();
    public void run(Object ... parameters);
    public void clean();
}

package task;

/**
 * this interface represent a general task.
 *
 * @param <T> the task type.
 */

public interface Task<T> {
    /**
     * this method runs the general task.
     *
     * @return unimplemented option.
     */
    T run();
}
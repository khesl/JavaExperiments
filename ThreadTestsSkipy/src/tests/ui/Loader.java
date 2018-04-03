package tests.ui;

/**
 * Geberal background loader interface with two operations - {@link #execute()} and {@link #cancel()}
 *
 * @author Eugene Matyushkin aka Skipy
 * @version $Id: Loader.java 416 2010-07-26 14:22:39Z skipy_ru $
 * @since 09.07.2010
 */
public interface Loader {

    /**
     * Performs loading operation in new thread
     */
    void execute();

    /**
     * Cancels current operation
     */
    void cancel();
}

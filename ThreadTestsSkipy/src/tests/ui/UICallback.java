package tests.ui;

/**
 * Callback class to use from {@link Loader} to publish data, perform UI changes etc. Thread safety
 * should be taken into account when implementing this interface
 *
 * @author Eugene Matyushkin aka Skipy
 * @version $Id: UICallback.java 422 2010-08-17 13:40:35Z skipy_ru $
 * @since 09.07.2010
 */
public interface UICallback {

    /**
     * Appends text to the main text area. This method can be called from outside EDT.
     *
     * @param text text to append
     */
    @RequiresEDT
    void appendText(String text);

    /**
     * Sets text to the main text area, replacing existing context. This method can be called from outside EDT.
     *
     * @param text text to set
     */
    @RequiresEDT
    void setText(String text);

    /**
     * Sets current progress. Values should be in the range [0,100]. This method can be called from outside EDT.
     *
     * @param progressPercent progress value to set
     */
    @RequiresEDT
    void setProgress(int progressPercent);

    /**
     * Performs required UI operations when loading starts. This method can be called from outside EDT.
     */
    @RequiresEDT
    void startLoading();

    /**
     * Performs required UI operations when loading stops. This method can be called from outside EDT.
     */
    @RequiresEDT
    void stopLoading();

    /**
     * Displays error message. This method can be called from outside EDT.
     *
     * @param message message to display
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void showError(String message);
}

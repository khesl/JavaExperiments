package tests.ui;

import java.io.IOException;

/**
 * Manually written background data loader. Loads text from the reader passed and puts it to the UI
 * through the callback passed. Note, that loader can be executed only once.
 *
 * @author Eugene Matyushkin aka Skipy
 * @version $Id: CustomLoader.java 416 2010-07-26 14:22:39Z skipy_ru $
 * @since 09.07.2010
 */
public class CustomLoader implements Runnable, Loader {

    /**
     * Execution flag
     */
    private boolean executed = false;

    /**
     * UI callback
     */
    protected UICallback ui;

    /**
     * Data source
     */
    private ResourceReader reader;

    /**
     * Loader cancelation flag
     */
    private boolean canceled = false;


    /**
     * Created custom loader.
     *
     * @param ui     UI callback to use when publishing data and manipulating UI
     * @param reader data source
     */
    public CustomLoader(UICallback ui, ResourceReader reader) {
        this.ui = ui;
        this.reader = reader;
    }

    /**
     * Starts this loader execution in separate thread
     */
    @Override
    public synchronized void execute() {
        if (executed) {
            throw new IllegalStateException("Loader is already executed");
        }
        executed = true;
        Thread t = new Thread(this, "Custom loader thread");
        t.start();
    }

    /**
     * Loader main cycle
     */
    @Override
    public void run() {
        ui.startLoading();
        try {
            StringBuilder sb = new StringBuilder();
            while (!isCanceled()) {
                String str = reader.getNextLine();
                if (str == null) {
                    break;
                }
                sb.append(str).append("\n");
                ui.setProgress(reader.getProgressPercent());
                ui.appendText(str + "\n");
            }
            ui.setText(sb.toString());
        } catch (IOException ex) {
            ui.showError("Error while reading data: " + ex.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                ui.showError("Error while reading data: " + ex.getMessage());
            }
            ui.stopLoading();
        }
    }

    /**
     * Cancels current loading
     */
    public synchronized void cancel() {
        canceled = true;
    }

    /**
     * Checks whether execution was cancelled
     *
     * @return check result
     */
    private synchronized boolean isCanceled() {
        return canceled;
    }
}

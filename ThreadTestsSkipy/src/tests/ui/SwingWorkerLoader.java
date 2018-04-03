package tests.ui;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Background data loader based on {@link javax.swing.SwingWorker}.
 *
 * @author Eugene Matyushkin aka Skipy
 * @version $Id: SwingWorkerLoader.java 416 2010-07-26 14:22:39Z skipy_ru $
 * @since 12.07.2010
 */
public class SwingWorkerLoader extends SwingWorker<String, String> implements Loader {

    /**
     * Buffer for data loading. This field is used in both background and ED threads,
     * that's why StringBuffer is used
     */
    private StringBuffer buffer = new StringBuffer();

    /**
     * UI callback
     */
    private UICallback ui;

    /**
     * Data source
     */
    private ResourceReader reader;

    /**
     * Creates data loader.
     *
     * @param ui     UI callback to use when publishing data and manipulating UI
     * @param reader data source
     */
    public SwingWorkerLoader(UICallback ui, ResourceReader reader) {
        this.ui = ui;
        this.reader = reader;
        // this operation is safe because
        // 1. SwingWorkerLoader is created in EDT
        // 2. Anyway - UICallback is proxied by EDTInvocationHandler  
        this.ui.startLoading();
    }

    /**
     * Background part of loader. This method is called in background thread. It reads data from data source and
     * places it to UI  by calling {@link javax.swing.SwingWorker#publish(Object[])}
     *
     * @return background execution result - all data loaded
     * @throws Exception if any error occures
     */
    @Override
    protected String doInBackground() throws Exception {
        try {
            while (!isCancelled()) {
                String str = reader.getNextLine();
                if (str == null) {
                    break;
                }
                buffer.append(str).append("\n");
                setProgress(reader.getProgressPercent());
                publish(str);
            }
        } catch (IOException ex) {
            ui.showError("Error while reading data: " + ex.getMessage());
        } finally {
            reader.close();
        }
        setProgress(100);
        return buffer.toString();
    }

    /**
     * EDT part of loader. This method is called in EDT
     *
     * @param chunks data, that was passed to UI in {@link #doInBackground()} by calling
     *               {@link javax.swing.SwingWorker#publish(Object[])}
     */
    @Override
    protected void process(List<String> chunks) {
        for (String line : chunks) {
            ui.appendText(line + "\n");
        }
        ui.setProgress(getProgress());
    }

    /**
     * Cancels execution
     */
    @Override
    public void cancel() {
        cancel(true);
    }

    /**
     * This method is called in EDT after {@link #doInBackground()} is finished.
     */
    @Override
    protected void done() {
        ui.stopLoading();
        ui.setText(buffer.toString());
    }
}

package tests.ui;

import ru.skipy.tests.io.CapacityUnit;
import ru.skipy.tests.io.ControllableCapacityInputStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;




/**
 * DiplomaProj UI frame that demonstrates UI coordination with background threads.
 *
 * @author Eugene Matyushkin aka Skipy
 * @version $Id: DiplomaProj.java 424 2010-08-17 16:06:48Z skipy_ru $
 * @since 07.07.2010
 */
public class Main extends JFrame {

    /**
     * iso-8859-1 encoding name
     */
    private static final String ENCODING_8859_1 = "iso-8859-1";

    /**
     * UTF-8 encoding name
     */
    private static final String ENCODING_UTF8 = "UTF-8";

    /**
     * Cp1251 encoding name
     */
    private static final String ENCODING_WIN1251 = "Cp1251";

    /**
     * Text area in which text is loaded
     */
    private JTextArea taText;

    /**
     * Loading progress indicator
     */
    private JProgressBar pb;

    /**
     * Loading status popup
     */
    private JPanel popup;

    /**
     * data loading maximum capacity
     */
    private int maxCapacity = 8;

    /**
     * data loading maximum capacity unit
     */
    private CapacityUnit unit = CapacityUnit.KiloBytesPerSecond;

    /**
     * whether to use SwingWorker
     */
    private boolean useSwingWorker = false;

    /**
     * Encoding to use
     */
    private String encoding = ENCODING_8859_1;

    /**
     * Loader to use
     */
    private Loader loader = null;

    /**
     * UI callback implementation
     */
    private class UICallbackImpl implements UICallback {

        /**
         * Appends the text passes to test area
         *
         * @param text text to append
         */
        @Override
        public void appendText(final String text) {
            taText.append(text);
        }

        /**
         * Sets the text passed to text area
         *
         * @param text text to set
         */
        @Override
        public void setText(final String text) {
            taText.setText(text);
        }

        /**
         * Sets loading progress
         *
         * @param progressPercent progress value to set
         */
        @Override
        public void setProgress(final int progressPercent) {
            pb.setValue(progressPercent);
        }

        /**
         * Performs visual operations on loading start - clears the text and shows popup with the progress bar .
         */
        @Override
        public void startLoading() {
            taText.setText("");
            pb.setValue(0);
            popup.setVisible(true);
        }

        /**
         * Performs visual operations on loading stop - hides progress bar
         */
        @Override
        public void stopLoading() {
            popup.setVisible(false);
            loader = null;
        }

        /**
         * Shows error message to user
         *
         * @param message message to display
         */
        @Override
        public void showError(final String message) {
            JOptionPane.showMessageDialog(Main.this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initiates file loading
     *
     * @param fileName name of the file to load
     */
    private void loadFile(String fileName) {
        File f = new File(fileName);
        UICallback ui = (UICallback) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{UICallback.class}, new EDTInvocationHandler(new UICallbackImpl()));
        if (!f.exists()) {
            ui.showError("File " + fileName + " doesn't exist!");
            return;
        }
        if (f.isDirectory()) {
            ui.showError("File " + fileName + " is a directory!");
            return;
        }
        ResourceReader reader;
        try {
            reader = new ResourceReader(
                    new ControllableCapacityInputStream(new FileInputStream(f), maxCapacity, unit),
                    f.length(),
                    encoding);
        } catch (UnsupportedEncodingException ex) {
            ui.showError("Encoding " + encoding + " is not supported by JVM!");
            return;
        } catch (FileNotFoundException ex) {
            ui.showError("File " + fileName + " doesn't exist!");
            return;
        }
        if (useSwingWorker) {
            loader = new SwingWorkerLoader(ui, reader);
        } else {
            loader = new CustomLoader(ui, reader);
        }
        loader.execute();
    }

    /**
     * Creates main application frame
     */
    public Main() {
        super("Background operations and UI synchronization test");
        taText = new JTextArea(10, 50);
        setJMenuBar(createMenuBar());
        JScrollPane sp = new JScrollPane(taText);
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(cp);
        cp.add(sp, BorderLayout.CENTER);

        popup = new JPanel(new BorderLayout(5, 5));
        popup.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        popup.setBackground(Color.white);
        getLayeredPane().add(popup);

        pb = new JProgressBar();
        popup.add(pb, BorderLayout.CENTER);
        pb.setBorderPainted(true);
        pb.setMinimum(0);
        pb.setMaximum(100);
        pb.setValue(0);
        pb.setStringPainted(true);
        pb.setIndeterminate(false);
        JButton cancel = new JButton("Cancel");
        popup.add(cancel, BorderLayout.EAST);
        popup.doLayout();
        Dimension size = popup.getPreferredSize();
        Dimension windowSize = new Dimension(800, 600);
        int popupWidth = 400;
        popup.setBounds((windowSize.width - popupWidth) / 2, (windowSize.height - size.height) / 2,
                popupWidth, size.height);
        getLayeredPane().setLayer(popup, JLayeredPane.POPUP_LAYER);
        popup.setVisible(false);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loader.cancel();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(windowSize);
        setLocationRelativeTo(null);
    }

    /**
     * Creates menu bar
     *
     * @return created menu bar
     */

    private JMenuBar createMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu mLoad = new JMenu("File");
        for (final String fileName : getTestFiles()) {
            JMenuItem mi = new JMenuItem(fileName);
            mi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadFile(fileName);
                }
            });
            mLoad.add(mi);
        }
        mb.add(mLoad);
        mb.add(createCapacityMenu());
        mb.add(createLoaderMenu());
        mb.add(createEncodingMenu());
        return mb;
    }

    /**
     * Creates capacity menu
     *
     * @return created menu
     */
    private JMenu createCapacityMenu() {
        JMenu m = new JMenu("Capacity");
        ButtonGroup bg = new ButtonGroup();
        JRadioButtonMenuItem mi = new JRadioButtonMenuItem("8 Kbps");
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxCapacity = 8;
                unit = CapacityUnit.KiloBytesPerSecond;
            }
        });
        mi.setSelected(true);

        mi = new JRadioButtonMenuItem("64 Kbps");
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxCapacity = 64;
                unit = CapacityUnit.KiloBytesPerSecond;
            }
        });
        mi.setSelected(false);

        mi = new JRadioButtonMenuItem("256 Kbps");
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxCapacity = 256;
                unit = CapacityUnit.KiloBytesPerSecond;
            }
        });
        mi.setSelected(false);
        return m;
    }

    /**
     * Creates loader menu
     *
     * @return created menu
     */
    private JMenu createLoaderMenu() {
        JMenu m = new JMenu("Loader");
        ButtonGroup bg = new ButtonGroup();

        JRadioButtonMenuItem mi = new JRadioButtonMenuItem("Custom");
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useSwingWorker = false;
            }
        });
        mi.setSelected(true);

        mi = new JRadioButtonMenuItem("SwingWorker");
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useSwingWorker = true;
            }
        });
        mi.setSelected(false);
        return m;
    }

    /**
     * Creates encoding menu
     *
     * @return created menu
     */
    private JMenu createEncodingMenu() {
        JMenu m = new JMenu("Encoding");
        ButtonGroup bg = new ButtonGroup();
        JRadioButtonMenuItem mi = new JRadioButtonMenuItem(ENCODING_8859_1);
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encoding = ENCODING_8859_1;
            }
        });
        mi.setSelected(true);

        mi = new JRadioButtonMenuItem(ENCODING_UTF8);
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encoding = ENCODING_UTF8;
            }
        });
        mi.setSelected(false);

        mi = new JRadioButtonMenuItem(ENCODING_WIN1251);
        bg.add(mi);
        m.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encoding = ENCODING_WIN1251;
            }
        });
        mi.setSelected(false);
        return m;
    }

    /**
     * Creates test files list - all files in current directory with .txt extension will be included
     *
     * @return test files' list
     */
    private List<String> getTestFiles() {
        File dir = new File(".");
        File[] textFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File name) {
                return !name.isDirectory() && name.getName().toLowerCase().endsWith(".txt");
            }
        });
        List<String> fileNames = new ArrayList<String>(textFiles.length);
        for (File f : textFiles) {
            fileNames.add(f.getName());
        }
        return fileNames;
    }

    /**
     * Starts application
     *
     * @param args application command line arguments
     */
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}

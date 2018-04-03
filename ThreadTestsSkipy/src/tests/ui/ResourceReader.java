package tests.ui;


import ru.skipy.tests.io.ControllableCapacityInputStream;
import java.io.*;


/**
 * General resource reading mechanism. Placed into separate class to avoid code duplication in loaders.
 *
 * @author Eugene Matyushkin aka Skipy
 * @version $Id: ResourceReader.java 416 2010-07-26 14:22:39Z skipy_ru $
 * @since 12.07.2010
 */
class ResourceReader {

    /**
     * Data source
     */
    protected ControllableCapacityInputStream dataSource;

    /**
     * Data length
     */
    private long dataLen;

    /**
     * Data reader
     */
    private BufferedReader srcReader;

    /**
     * Constructs resource reader instance.
     *
     * @param is       controllable capacity input stream to read from
     * @param dataLen  data length
     * @param encoding stream encoding for proper text reading
     * @throws UnsupportedEncodingException if encoding is not supported
     */
    ResourceReader(ControllableCapacityInputStream is, long dataLen, String encoding) throws UnsupportedEncodingException {
        srcReader = new BufferedReader(new InputStreamReader(is, encoding));
        this.dataSource = is;
        this.dataLen = dataLen;
    }

    /**
     * Reads next line
     *
     * @return next line or <code>null</code> if end of stream is reached
     * @throws IOException if error occures
     */
    String getNextLine() throws IOException {
        return srcReader.readLine();
    }

    /**
     * Returns progress of data reading
     *
     * @return current progress value
     */
    int getProgressPercent() {
        long read = dataSource.getBytesRead();
        return (int) (((double) read) / ((double) dataLen) * 100.);
    }

    /**
     * Closes the resource reader along with underlying stream
     *
     * @throws IOException if error occures while closing
     */
    void close() throws IOException {
        try {
            srcReader.close();
        } finally {
            dataSource.close();
        }
    }
}

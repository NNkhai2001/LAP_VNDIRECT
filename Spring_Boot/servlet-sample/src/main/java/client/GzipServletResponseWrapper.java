package client;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GzipServletResponseWrapper extends HttpServletResponseWrapper {
    private GZipServletOutputStream stream = null;
    private PrintWriter writer = null;

    public GzipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() throws IOException {
        writer.close();
        stream.close();
    }

    public void flushBuffer() throws IOException {
        if (writer != null) writer.flush();
        stream.flush();
    }

    GZipServletOutputStream getOutStream() throws IOException {
        if (stream != null) return stream;
        stream = new GZipServletOutputStream(getResponse().getOutputStream());
        return stream;
    }

    public PrintWriter getWriter() throws IOException {

        if (writer == null) {
            stream = (GZipServletOutputStream) getOutStream();
            String encoding = getResponse().getCharacterEncoding();
            writer = new PrintWriter(new OutputStreamWriter(stream, encoding));
        }
        return writer;
    }
}


package client;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {
    private GZIPOutputStream stream = null;

    public GZipServletOutputStream(OutputStream output) throws IOException {
        stream = new GZIPOutputStream(output);
    }

    public void close() throws IOException {
        stream.close();
    }

    public void plush() throws IOException {
        stream.flush();
    }

    public void write(byte[] bytes) throws IOException {
        stream.write(bytes);
    }

    public void write(byte[] bytes, int off, int len) throws IOException {
        for (int i = off; i < len; i++) {
            stream.write(bytes);
        }
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        stream.write(b);
    }
}

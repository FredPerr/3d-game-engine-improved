package ca.fredericperron.untitledgame.util;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class ResourceUtil {

    public String getAppDataFolderPath() {
        return System.getenv("APPDATA");
    }

    public boolean exists(String path) {
        return new File(path).exists();
    }

    public String convertByteArrayToString(byte[] data) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream(data.length);
            result.write(data, 0, data.length);
            return result.toString(StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeFile(String path, byte[] data, boolean replace) {
        if (!replace && exists(path))
            return;

        try {
            new File(path).getParentFile().mkdirs();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(path));
            out.write(data);
            if (out != null)
                out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] loadData(InputStream is) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1)
                result.write(buffer, 0, length);
            result.close();
            is.close();
            return result.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] loadData(String path) {
        if (!exists(path))
            return null;
        try {
            return loadData(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream getInputStream(String path) {
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null)
            throw new NullPointerException(String.format("The InputStream from the path %s is not reachable. You might want to try with a slash '/' at the beginning of the path to search from absolute path in the file.", path));
        return is;
    }


    private static ResourceUtil instance;

    public static ResourceUtil getInstance() {
        return instance == null ? instance = new ResourceUtil() : instance;
    }
}


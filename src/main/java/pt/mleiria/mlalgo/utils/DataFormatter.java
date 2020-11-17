package pt.mleiria.mlalgo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class DataFormatter {

    private static final Logger LOG = Logger.getLogger(DataFormatter.class.getName());
    public static final String EMPTY_SET = "[{}]";

    private DataFormatter() {

    }


    /**
     * [ {x:0,y:100},{x:10,y:110}]
     *
     * @param x
     * @param y
     * @return
     */
    public static String formatxy(Double[] x, Double[] y) {

        if (x == null || y == null) {
            return EMPTY_SET;
        }

        final StringBuilder sb = new StringBuilder("[");
        final int len = y.length;
        for (int i = 0; i < len - 1; i++) {
            sb.append("{x:").append(x[i]).append(",y:").append(y[i]).append("},");
        }
        sb.append("{x:").append(x[len - 1]).append(",y:").append(y[len - 1]).append("}]");
        return sb.toString();
    }

    /**
     * @param value
     * @return
     */
    public static String urlEncodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

}

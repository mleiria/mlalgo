/**
 *
 */
package pt.mleiria.mlalgo.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static java.lang.System.arraycopy;

/**
 * @author manuel
 *
 */
public final class VUtils<N extends Number> {
    /**
     *
     * @param array
     * @return
     */
    public String showContents(N[] array) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                sb.append(array[i]);
            } else {
                sb.append(array[i]).append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param arr
     * @return
     */
    public String showContents(N[][] arr) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (final N[] arr1 : arr) {
            sb.append("[ ");
            for (final N element : arr1) {
                sb.append(element).append(" ");
            }
            sb.append("]");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param array
     * @return
     */
    public static String showArrayContents(double[] array) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                sb.append(array[i]);
            } else {
                sb.append(array[i]).append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     *
     * @param arr
     * @return
     */
    public static String showArrayContents(int[] array) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                sb.append(array[i]);
            } else {
                sb.append(array[i]).append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     *
     * @param arr
     * @return
     */
    public static String showArrayContents(String[] arr) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length - 1; i++) {
            sb.append(arr[i]).append(";");
        }
        sb.append(arr[arr.length - 1]).append("]");
        return sb.toString();
    }

    /**
     *
     * @param arr
     * @return
     */
    public static String showArrayContents(int[][] arr) {
        final StringBuilder sb = new StringBuilder("\n");
        for (final int[] arr1 : arr) {
            for (final int element : arr1) {
                sb.append(element).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param arr
     * @return
     */
    public static String showArrayContents(double[][] arr) {
        final StringBuilder sb = new StringBuilder("\n");
        for (final double[] arr1 : arr) {
            for (final double element : arr1) {
                sb.append(element).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param arr
     * @return
     */
    public static String showArrayContents(byte[] arr) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length - 1; i++) {
            sb.append((char) arr[i]).append(";");
        }
        sb.append(arr[arr.length - 1]).append("]");
        return sb.toString();
    }

    /**
     *
     * @param map
     * @return
     */
    public static String showSeparatedByClass(final Map<Integer, List<Double[]>> map) {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<Integer, List<Double[]>> entry : map.entrySet()) {
            sb.append("\nKey:").append(entry.getKey()).append("\n");
            final List<Double[]> valueLst = entry.getValue();
            for (final Double[] d : valueLst) {
                sb.append("[");
                for (int i = 0; i < d.length - 1; i++) {
                    sb.append(d[i] + ":");
                }
                sb.append(d[d.length - 1]).append("]\n");
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param data
     * @return
     */
    public static String showHtmlTable(final List<String[]> data) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");
        for (final String[] row : data) {
            sb.append("<tr>");
            for (final String str : row) {
                sb.append("<td>" + str + "</td>");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");
        return sb.toString();
    }

    /**
     *
     * @param data
     * @param numColsToShow
     * @return
     */
    public static String showHtmlTable(final List<String[]> data, final int numColsToShow) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");
        for (final String[] row : data) {
            final String[] subRow = new String[numColsToShow];

            arraycopy(row, 0, subRow, 0, numColsToShow);
            sb.append("<tr>");
            for (final String str : subRow) {
                sb.append("<td>" + str + "</td>");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");
        return sb.toString();
    }

    /**
     *
     * @param data
     * @param colIndex
     * @return
     */
    public static String showArrayPyFormat(final List<String[]> data, int colIndex) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (final String[] row : data) {
            sb.append(row[colIndex]);
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String formatNumber(final int num){
        final String pattern = "###,###.###";
        final DecimalFormat decimalFormat = new DecimalFormat(pattern);

        return decimalFormat.format(num);
    }
}

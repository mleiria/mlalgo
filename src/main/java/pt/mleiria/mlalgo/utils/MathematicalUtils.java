/**
 *
 */
package pt.mleiria.mlalgo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author manuel
 *
 */
public class MathematicalUtils {

    private MathematicalUtils() {
    }

    /**
     *
     * @param value
     * @param places
     * @return
     */
    public static double round(final double value, final int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf((value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}

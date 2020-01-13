/**
 *
 */
package pt.mleiria.mlalgo.stats;

import org.apache.commons.math3.stat.StatUtils;
import pt.mleiria.mlalgo.utils.Arrays1D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author manuel
 *
 */
public class AttributeStatistics {

    private final double[] values;


    /**
     *
     * @param values
     */
    public AttributeStatistics(final Double[] values) {
        this.values = Arrays1D.unBox(values);
    }

    /**
     *
     * @return
     */
    public double getMax() {
        return StatUtils.max(values);
    }

    /**
     *
     * @return
     */
    public double getMin() {
        return StatUtils.min(values);
    }

    /**
     *
     * @return
     */
    public double getMean() {
        return StatUtils.mean(values);
    }

    /**
     *
     * @return
     */
    public double getVariance() {
        return StatUtils.variance(values);
    }

    /**
     * a key based on a value (bidi maps)
     * @param labelHolder
     * @param labelsY
     * @return
     */
    public Map<String, Integer> getCount(final Map<String, Integer> labelHolder, Double[] labelsY) {
        final Map<String, Integer> res = new HashMap<>();
        for (final Double d : labelsY) {
            final String key = labelHolder.entrySet()
                    .stream()
                    .filter(elem -> elem.getValue() == d.intValue())
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new)
                    .getKey();
            res.merge(key, 1, (a, b) -> a + b);
        }
        return res;
    }

}

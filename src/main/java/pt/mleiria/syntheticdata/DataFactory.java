/**
 *
 */
package pt.mleiria.syntheticdata;

import org.apache.commons.math3.distribution.NormalDistribution;
import pt.mleiria.mlalgo.utils.Tuple2;

/**
 * @author manuel
 *
 */
public final class DataFactory {

    public static Tuple2<Double[][], Double[]> generateTwoClasses(final NormalDistribution nd1,
                                                                  final NormalDistribution nd2, final int numRows, final int numCols) {

        final Double[][] data = new Double[numRows][numCols];
        final Double[] target = new Double[numRows];

        // data set in class 1
        for (int i = 0; i < (numRows / 2); i++) {
            data[i][0] = nd1.sample();
            data[i][1] = nd2.sample();
            target[i] = 1.;
        }

        // data set in class 2
        for (int i = numRows / 2; i < numRows; i++) {
            data[i][0] = nd2.sample();
            data[i][1] = nd1.sample();
            target[i] = -1.;
        }


        return new Tuple2<>(data, target);
    }

    public static Tuple2<Double[], Double[]> generateExponentialCurve(final int size){
        final Double[] x = new Double[size];
        final Double[] y = new Double[size];
        for(int i = 0; i < size; i++){
            x[i] = Double.valueOf(i);
            y[i] = Math.exp(x[i]);
        }
        return new Tuple2<>(x,y);
    }

    public static Tuple2<Double[][],Double[]> loadIrisDataset() {
        final Double[][] irisDatasetX = new Double[150][4];
        irisDatasetX[0] = new Double[]{5.1, 3.5, 1.4, 0.2,};
        irisDatasetX[1] = new Double[]{4.9, 3.0, 1.4, 0.2,};
        irisDatasetX[2] = new Double[]{4.7, 3.2, 1.3, 0.2,};
        irisDatasetX[3] = new Double[]{4.6, 3.1, 1.5, 0.2,};
        irisDatasetX[4] = new Double[]{5.0, 3.6, 1.4, 0.2,};
        irisDatasetX[5] = new Double[]{5.4, 3.9, 1.7, 0.4,};
        irisDatasetX[6] = new Double[]{4.6, 3.4, 1.4, 0.3,};
        irisDatasetX[7] = new Double[]{5.0, 3.4, 1.5, 0.2,};
        irisDatasetX[8] = new Double[]{4.4, 2.9, 1.4, 0.2,};
        irisDatasetX[9] = new Double[]{4.9, 3.1, 1.5, 0.1,};
        irisDatasetX[10] = new Double[]{5.4, 3.7, 1.5, 0.2,};
        irisDatasetX[11] = new Double[]{4.8, 3.4, 1.6, 0.2,};
        irisDatasetX[12] = new Double[]{4.8, 3.0, 1.4, 0.1,};
        irisDatasetX[13] = new Double[]{4.3, 3.0, 1.1, 0.1,};
        irisDatasetX[14] = new Double[]{5.8, 4.0, 1.2, 0.2,};
        irisDatasetX[15] = new Double[]{5.7, 4.4, 1.5, 0.4,};
        irisDatasetX[16] = new Double[]{5.4, 3.9, 1.3, 0.4,};
        irisDatasetX[17] = new Double[]{5.1, 3.5, 1.4, 0.3,};
        irisDatasetX[18] = new Double[]{5.7, 3.8, 1.7, 0.3,};
        irisDatasetX[19] = new Double[]{5.1, 3.8, 1.5, 0.3,};
        irisDatasetX[20] = new Double[]{5.4, 3.4, 1.7, 0.2,};
        irisDatasetX[21] = new Double[]{5.1, 3.7, 1.5, 0.4,};
        irisDatasetX[22] = new Double[]{4.6, 3.6, 1.0, 0.2,};
        irisDatasetX[23] = new Double[]{5.1, 3.3, 1.7, 0.5,};
        irisDatasetX[24] = new Double[]{4.8, 3.4, 1.9, 0.2,};
        irisDatasetX[25] = new Double[]{5.0, 3.0, 1.6, 0.2,};
        irisDatasetX[26] = new Double[]{5.0, 3.4, 1.6, 0.4,};
        irisDatasetX[27] = new Double[]{5.2, 3.5, 1.5, 0.2,};
        irisDatasetX[28] = new Double[]{5.2, 3.4, 1.4, 0.2,};
        irisDatasetX[29] = new Double[]{4.7, 3.2, 1.6, 0.2,};
        irisDatasetX[30] = new Double[]{4.8, 3.1, 1.6, 0.2,};
        irisDatasetX[31] = new Double[]{5.4, 3.4, 1.5, 0.4,};
        irisDatasetX[32] = new Double[]{5.2, 4.1, 1.5, 0.1,};
        irisDatasetX[33] = new Double[]{5.5, 4.2, 1.4, 0.2,};
        irisDatasetX[34] = new Double[]{4.9, 3.1, 1.5, 0.2,};
        irisDatasetX[35] = new Double[]{5.0, 3.2, 1.2, 0.2,};
        irisDatasetX[36] = new Double[]{5.5, 3.5, 1.3, 0.2,};
        irisDatasetX[37] = new Double[]{4.9, 3.6, 1.4, 0.1,};
        irisDatasetX[38] = new Double[]{4.4, 3.0, 1.3, 0.2,};
        irisDatasetX[39] = new Double[]{5.1, 3.4, 1.5, 0.2,};
        irisDatasetX[40] = new Double[]{5.0, 3.5, 1.3, 0.3,};
        irisDatasetX[41] = new Double[]{4.5, 2.3, 1.3, 0.3,};
        irisDatasetX[42] = new Double[]{4.4, 3.2, 1.3, 0.2,};
        irisDatasetX[43] = new Double[]{5.0, 3.5, 1.6, 0.6,};
        irisDatasetX[44] = new Double[]{5.1, 3.8, 1.9, 0.4,};
        irisDatasetX[45] = new Double[]{4.8, 3.0, 1.4, 0.3,};
        irisDatasetX[46] = new Double[]{5.1, 3.8, 1.6, 0.2,};
        irisDatasetX[47] = new Double[]{4.6, 3.2, 1.4, 0.2,};
        irisDatasetX[48] = new Double[]{5.3, 3.7, 1.5, 0.2,};
        irisDatasetX[49] = new Double[]{5.0, 3.3, 1.4, 0.2,};
        irisDatasetX[50] = new Double[]{7.0, 3.2, 4.7, 1.4,};
        irisDatasetX[51] = new Double[]{6.4, 3.2, 4.5, 1.5,};
        irisDatasetX[52] = new Double[]{6.9, 3.1, 4.9, 1.5,};
        irisDatasetX[53] = new Double[]{5.5, 2.3, 4.0, 1.3,};
        irisDatasetX[54] = new Double[]{6.5, 2.8, 4.6, 1.5,};
        irisDatasetX[55] = new Double[]{5.7, 2.8, 4.5, 1.3,};
        irisDatasetX[56] = new Double[]{6.3, 3.3, 4.7, 1.6,};
        irisDatasetX[57] = new Double[]{4.9, 2.4, 3.3, 1.0,};
        irisDatasetX[58] = new Double[]{6.6, 2.9, 4.6, 1.3,};
        irisDatasetX[59] = new Double[]{5.2, 2.7, 3.9, 1.4,};
        irisDatasetX[60] = new Double[]{5.0, 2.0, 3.5, 1.0,};
        irisDatasetX[61] = new Double[]{5.9, 3.0, 4.2, 1.5,};
        irisDatasetX[62] = new Double[]{6.0, 2.2, 4.0, 1.0,};
        irisDatasetX[63] = new Double[]{6.1, 2.9, 4.7, 1.4,};
        irisDatasetX[64] = new Double[]{5.6, 2.9, 3.6, 1.3,};
        irisDatasetX[65] = new Double[]{6.7, 3.1, 4.4, 1.4,};
        irisDatasetX[66] = new Double[]{5.6, 3.0, 4.5, 1.5,};
        irisDatasetX[67] = new Double[]{5.8, 2.7, 4.1, 1.0,};
        irisDatasetX[68] = new Double[]{6.2, 2.2, 4.5, 1.5,};
        irisDatasetX[69] = new Double[]{5.6, 2.5, 3.9, 1.1,};
        irisDatasetX[70] = new Double[]{5.9, 3.2, 4.8, 1.8,};
        irisDatasetX[71] = new Double[]{6.1, 2.8, 4.0, 1.3,};
        irisDatasetX[72] = new Double[]{6.3, 2.5, 4.9, 1.5,};
        irisDatasetX[73] = new Double[]{6.1, 2.8, 4.7, 1.2,};
        irisDatasetX[74] = new Double[]{6.4, 2.9, 4.3, 1.3,};
        irisDatasetX[75] = new Double[]{6.6, 3.0, 4.4, 1.4,};
        irisDatasetX[76] = new Double[]{6.8, 2.8, 4.8, 1.4,};
        irisDatasetX[77] = new Double[]{6.7, 3.0, 5.0, 1.7,};
        irisDatasetX[78] = new Double[]{6.0, 2.9, 4.5, 1.5,};
        irisDatasetX[79] = new Double[]{5.7, 2.6, 3.5, 1.0,};
        irisDatasetX[80] = new Double[]{5.5, 2.4, 3.8, 1.1,};
        irisDatasetX[81] = new Double[]{5.5, 2.4, 3.7, 1.0,};
        irisDatasetX[82] = new Double[]{5.8, 2.7, 3.9, 1.2,};
        irisDatasetX[83] = new Double[]{6.0, 2.7, 5.1, 1.6,};
        irisDatasetX[84] = new Double[]{5.4, 3.0, 4.5, 1.5,};
        irisDatasetX[85] = new Double[]{6.0, 3.4, 4.5, 1.6,};
        irisDatasetX[86] = new Double[]{6.7, 3.1, 4.7, 1.5,};
        irisDatasetX[87] = new Double[]{6.3, 2.3, 4.4, 1.3,};
        irisDatasetX[88] = new Double[]{5.6, 3.0, 4.1, 1.3,};
        irisDatasetX[89] = new Double[]{5.5, 2.5, 4.0, 1.3,};
        irisDatasetX[90] = new Double[]{5.5, 2.6, 4.4, 1.2,};
        irisDatasetX[91] = new Double[]{6.1, 3.0, 4.6, 1.4,};
        irisDatasetX[92] = new Double[]{5.8, 2.6, 4.0, 1.2,};
        irisDatasetX[93] = new Double[]{5.0, 2.3, 3.3, 1.0,};
        irisDatasetX[94] = new Double[]{5.6, 2.7, 4.2, 1.3,};
        irisDatasetX[95] = new Double[]{5.7, 3.0, 4.2, 1.2,};
        irisDatasetX[96] = new Double[]{5.7, 2.9, 4.2, 1.3,};
        irisDatasetX[97] = new Double[]{6.2, 2.9, 4.3, 1.3,};
        irisDatasetX[98] = new Double[]{5.1, 2.5, 3.0, 1.1,};
        irisDatasetX[99] = new Double[]{5.7, 2.8, 4.1, 1.3,};
        irisDatasetX[100] = new Double[]{6.3, 3.3, 6.0, 2.5,};
        irisDatasetX[101] = new Double[]{5.8, 2.7, 5.1, 1.9,};
        irisDatasetX[102] = new Double[]{7.1, 3.0, 5.9, 2.1,};
        irisDatasetX[103] = new Double[]{6.3, 2.9, 5.6, 1.8,};
        irisDatasetX[104] = new Double[]{6.5, 3.0, 5.8, 2.2,};
        irisDatasetX[105] = new Double[]{7.6, 3.0, 6.6, 2.1,};
        irisDatasetX[106] = new Double[]{4.9, 2.5, 4.5, 1.7,};
        irisDatasetX[107] = new Double[]{7.3, 2.9, 6.3, 1.8,};
        irisDatasetX[108] = new Double[]{6.7, 2.5, 5.8, 1.8,};
        irisDatasetX[109] = new Double[]{7.2, 3.6, 6.1, 2.5,};
        irisDatasetX[110] = new Double[]{6.5, 3.2, 5.1, 2.0,};
        irisDatasetX[111] = new Double[]{6.4, 2.7, 5.3, 1.9,};
        irisDatasetX[112] = new Double[]{6.8, 3.0, 5.5, 2.1,};
        irisDatasetX[113] = new Double[]{5.7, 2.5, 5.0, 2.0,};
        irisDatasetX[114] = new Double[]{5.8, 2.8, 5.1, 2.4,};
        irisDatasetX[115] = new Double[]{6.4, 3.2, 5.3, 2.3,};
        irisDatasetX[116] = new Double[]{6.5, 3.0, 5.5, 1.8,};
        irisDatasetX[117] = new Double[]{7.7, 3.8, 6.7, 2.2,};
        irisDatasetX[118] = new Double[]{7.7, 2.6, 6.9, 2.3,};
        irisDatasetX[119] = new Double[]{6.0, 2.2, 5.0, 1.5,};
        irisDatasetX[120] = new Double[]{6.9, 3.2, 5.7, 2.3,};
        irisDatasetX[121] = new Double[]{5.6, 2.8, 4.9, 2.0,};
        irisDatasetX[122] = new Double[]{7.7, 2.8, 6.7, 2.0,};
        irisDatasetX[123] = new Double[]{6.3, 2.7, 4.9, 1.8,};
        irisDatasetX[124] = new Double[]{6.7, 3.3, 5.7, 2.1,};
        irisDatasetX[125] = new Double[]{7.2, 3.2, 6.0, 1.8,};
        irisDatasetX[126] = new Double[]{6.2, 2.8, 4.8, 1.8,};
        irisDatasetX[127] = new Double[]{6.1, 3.0, 4.9, 1.8,};
        irisDatasetX[128] = new Double[]{6.4, 2.8, 5.6, 2.1,};
        irisDatasetX[129] = new Double[]{7.2, 3.0, 5.8, 1.6,};
        irisDatasetX[130] = new Double[]{7.4, 2.8, 6.1, 1.9,};
        irisDatasetX[131] = new Double[]{7.9, 3.8, 6.4, 2.0,};
        irisDatasetX[132] = new Double[]{6.4, 2.8, 5.6, 2.2,};
        irisDatasetX[133] = new Double[]{6.3, 2.8, 5.1, 1.5,};
        irisDatasetX[134] = new Double[]{6.1, 2.6, 5.6, 1.4,};
        irisDatasetX[135] = new Double[]{7.7, 3.0, 6.1, 2.3,};
        irisDatasetX[136] = new Double[]{6.3, 3.4, 5.6, 2.4,};
        irisDatasetX[137] = new Double[]{6.4, 3.1, 5.5, 1.8,};
        irisDatasetX[138] = new Double[]{6.0, 3.0, 4.8, 1.8,};
        irisDatasetX[139] = new Double[]{6.9, 3.1, 5.4, 2.1,};
        irisDatasetX[140] = new Double[]{6.7, 3.1, 5.6, 2.4,};
        irisDatasetX[141] = new Double[]{6.9, 3.1, 5.1, 2.3,};
        irisDatasetX[142] = new Double[]{5.8, 2.7, 5.1, 1.9,};
        irisDatasetX[143] = new Double[]{6.8, 3.2, 5.9, 2.3,};
        irisDatasetX[144] = new Double[]{6.7, 3.3, 5.7, 2.5,};
        irisDatasetX[145] = new Double[]{6.7, 3.0, 5.2, 2.3,};
        irisDatasetX[146] = new Double[]{6.3, 2.5, 5.0, 1.9,};
        irisDatasetX[147] = new Double[]{6.5, 3.0, 5.2, 2.0,};
        irisDatasetX[148] = new Double[]{6.2, 3.4, 5.4, 2.3,};
        irisDatasetX[149] = new Double[]{5.9, 3.0, 5.1, 1.8,};

        final Double[] irisDatasetY = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0};
        return new Tuple2<>(irisDatasetX, irisDatasetY);
    }


}

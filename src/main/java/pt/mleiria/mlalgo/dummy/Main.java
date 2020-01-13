package pt.mleiria.mlalgo.dummy;

import pt.mleiria.mlalgo.utils.Arrays2D;
import pt.mleiria.mlalgo.utils.StopWatch;
import pt.mleiria.mlalgo.utils.VUtils;

public class Main {

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        v2();
        System.out.println(sw.elapsedTime());
    }

    private static void v2() {
        int numRows = 9999999;
        int numCols = 10;
        int numFolders = 150;
        int length = numRows / numFolders;

        Double[][] matrix = Arrays2D.genMatrix(numRows, numCols);
        Double[][][] tensor = new Double[numFolders][][];

        int startIndex = 0;
        int endIndex = length;

        for (int i = 0; i < numFolders; i++) {

            int cnt = 0;
            if (i == numFolders - 1) {
                endIndex += numRows % numFolders;
                tensor[i] = new Double[numRows - startIndex][numCols];
            } else {
                tensor[i] = new Double[endIndex - startIndex][numCols];
            }

            for (int j = startIndex; j < endIndex; j++) {
                System.arraycopy(matrix[j], 0, tensor[i][cnt], 0, matrix[j].length);
                cnt++;
            }
            startIndex = endIndex;
            endIndex = endIndex + length;
        }
    }

    private static void funciona() {
        int numRows = 9999999;
        int numCols = 10;
        int numFolders = 150;
        int length = numRows / numFolders;
        System.out.println(numRows % numFolders);

        Double[][] matrix = Arrays2D.genMatrix(numRows, numCols);
        //System.out.println(new VUtils<>().showContents(matrix));
        Double[][][] tensor = new Double[numFolders][][];

        int startIndex = 0;
        int endIndex = length;

        for (int i = 0; i < numFolders; i++) {

            int cnt = 0;
            if (i == numFolders - 1) {
                endIndex += numRows % numFolders;
                tensor[i] = new Double[numRows - startIndex][numCols];
            } else {
                tensor[i] = new Double[endIndex - startIndex][numCols];
            }

            for (int j = startIndex; j < endIndex; j++) {
                System.arraycopy(matrix[j], 0, tensor[i][cnt], 0, matrix[j].length);
                cnt++;
            }
            startIndex = endIndex;
            endIndex = endIndex + length;
        }
        System.out.println("stop");
    }

    /**
     * Se der divisao exacta funciona, cc nao
     */
    private static void v1() {
        int numRows = 13;
        int numCols = 5;
        int numFolders = 3;
        int length = numRows / numFolders;

        Double[][] matrix = Arrays2D.genMatrix(numRows, numCols);
        System.out.println(new VUtils<>().showContents(matrix));
        Double[][][] tensor = new Double[numFolders][length][numCols];

        int startIndex = 0;
        int endIndex = length;

        for (int i = 0; i < numFolders; i++) {
            int cnt = 0;
            for (int j = startIndex; j < endIndex; j++) {
                System.arraycopy(matrix[j], 0, tensor[i][cnt], 0, matrix[j].length);
                cnt++;
            }
            startIndex = endIndex;
            endIndex = endIndex + length;
        }
        System.out.println("stop");
    }

}

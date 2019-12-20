/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.dataset;

import static java.lang.Double.valueOf;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.mleiria.mlalgo.utils.Arrays1D;

/**
 * Incluir uma DESCR, DATA (features double[][]), TARGET (double[])
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class Dataset {

    private static final Logger LOG = Logger.getLogger(Dataset.class.getName());

    public Double[][] featuresX;
    public Double[] labelsY;
    private String[] header;

    private final Map<String, Integer> labelHolder;
    private int rowIndex = 0;
    private final String file;
    private boolean isLabelConversion;
    private final String separator;
    private final boolean hasRowHeader;
    private boolean isHeaderLoaded;
    final boolean isLabelInBeginning;

    /**
     * @param file
     * @param hasRowHeader
     * @param isLabelConversion
     * @param separator
     */
    public Dataset(final String file, final boolean hasRowHeader, final boolean isLabelConversion,
                   final String separator, final boolean isLabelInBeginning) {
        this.file = file;
        this.hasRowHeader = hasRowHeader;
        this.isLabelConversion = isLabelConversion;
        this.separator = separator;
        this.labelHolder = new HashMap<>();
        this.isLabelInBeginning = isLabelInBeginning;
    }

    /**
     *
     */
    public void loadDataset() {
        try (Stream<String> stream = lines(get(file))) {
            int rows = lineCounter();
            if (hasRowHeader) {
                rows--;
            }
            featuresX = new Double[rows][];
            labelsY = new Double[rows];
            stream.forEach(line -> loadRow(line));

        } catch (final IOException ex) {
            LOG.log(Level.SEVERE, "File Not Found: {0}", ex.getMessage());
        }
        /*
         * If header is null, create artificial header
         */
        if (header == null) {
            artificialHeader();
        }
        /*
         * Swap first col of featuresX with labelsY
         */
        if (isLabelInBeginning) {
            Double[] tgt = Arrays1D.getColumn(featuresX, 0);
            for (int i = 0; i < featuresX.length; i++) {
                featuresX[i][0] = labelsY[i];
            }
            labelsY = tgt;
            final String tmpTgtLabel = header[0];
            for (int i = 0; i < header.length - 1; i++) {
                header[i] = header[i + 1];
            }
            header[header.length - 1] = tmpTgtLabel;
        }
    }

    /**
     *
     */
    private void artificialHeader() {
        final int size = featuresX[0].length + 1;
        header = new String[size];
        for (int i = 0; i < size; i++) {
            header[i] = "Attr_" + (i + 1);
        }
        header[size - 1] = "tgtLabel";
    }

    /**
     * @return
     */
    public Map<String, Integer> getLabelHolder() {
        return labelHolder;
    }

    /**
     * @param line
     */
    private void loadRow(final String line) {

        final String[] tmp = line.split(separator);
        if (hasRowHeader && !isHeaderLoaded) {
            isHeaderLoaded = true;
            header = new String[tmp.length];
            System.arraycopy(tmp, 0, header, 0, tmp.length);
            return;
        }
        final Double[] tmpRow = new Double[tmp.length - 1];
        for (int i = 0; i < tmp.length - 1; i++) {
            tmpRow[i] = valueOf(tmp[i]);
        }
        final int lastColumnIndex = tmp.length - 1;
        loadLabel(tmp[lastColumnIndex]);
        featuresX[rowIndex] = tmpRow;
        rowIndex++;
    }

    /**
     * @param elem
     */
    private void loadLabel(String elem) {
        try {
            if (isLabelConversion) {
                convertLabel(elem);
            } else {
                labelsY[rowIndex] = valueOf(elem);
            }
        } catch (final NumberFormatException e) {
            isLabelConversion = true;
            convertLabel(elem);
        }
    }

    /**
     * @param label
     */
    private void convertLabel(final String label) {
        final int newNumericLabel = labelHolder.size();
        labelHolder.putIfAbsent(label, newNumericLabel);
        labelsY[rowIndex] = valueOf(labelHolder.get(label));
    }

    /**
     * @param file
     * @return Number of lines in file
     * @throws FileNotFoundException
     */
    private int lineCounter() throws IOException {
        try (Stream<String> stream = lines(get(file))) {
            return (int) stream.count();
        }
    }

    /**
     * @return the number of rows in the Dataset
     */
    public int size() {
        return labelsY.length;
    }

    /**
     * @return
     */
    public String[] getHeader() {
        return header;
    }

    /**
     * @return
     */
    public boolean isTargetNominal() {
        return isLabelConversion;
    }

    public <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

}

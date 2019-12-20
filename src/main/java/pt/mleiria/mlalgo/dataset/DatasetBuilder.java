/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.dataset;

/**
 * @author manuel
 */
public class DatasetBuilder {

    private String file;
    private boolean hasRowHeader;
    private boolean isLabelConversion;
    private String separator;
    private boolean isLabelInBeginning;

    /**
     * Defaults to classical csv
     * No header
     * Separated by ,
     * No label conversion
     */
    public DatasetBuilder(final String file) {
        this.file = file;
        hasRowHeader = false;
        isLabelConversion = false;
        separator = ",";
    }

    public DatasetBuilder setFile(final String file) {
        this.file = file;
        return this;
    }

    /**
     * Default is false
     *
     * @param hasRowHeader
     * @return
     */
    public DatasetBuilder setHasRowHeader(final boolean hasRowHeader) {
        this.hasRowHeader = hasRowHeader;
        return this;
    }

    /**
     * Defualts to false
     *
     * @param isLabelConversion
     * @return
     */
    public DatasetBuilder setIsLabelConversion(final boolean isLabelConversion) {
        this.isLabelConversion = isLabelConversion;
        return this;
    }

    /**
     * Default is ,
     *
     * @param separator
     * @return
     */
    public DatasetBuilder setSeparator(final String separator) {
        this.separator = separator;
        return this;
    }

    /**
     * Defualt is false
     *
     * @param isLabelInBeginning
     * @return
     */
    public DatasetBuilder setIsLabelInBeginning(final boolean isLabelInBeginning) {
        this.isLabelInBeginning = isLabelInBeginning;
        return this;
    }

    public Dataset createDataSet() {
        return new Dataset(file, hasRowHeader, isLabelConversion, separator, isLabelInBeginning);
    }
}

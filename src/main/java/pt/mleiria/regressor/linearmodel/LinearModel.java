package pt.mleiria.regressor.linearmodel;

public interface LinearModel<T> {

    T intercept();

    T[] coef();

    //void setFitIntercept(boolean fitIntercept);

    //void setNormalize(boolean normalize);
}

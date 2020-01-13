/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.regressor.linearmodel;

import pt.mleiria.mlalgo.core.Estimator;

import java.util.logging.Logger;

/**
 * @author manuel
 */
public class BatchGDRegressor implements Estimator {

    private static final Logger LOG = Logger.getLogger(BatchGDRegressor.class.getName());
/*
    @Override
    public Estimator fit(Double[][] xTrain, Double[] yTrain) {
                
        double eta = 0.1;
        int numIterations = 1000;
        int m = 100;
        
        RealMatrix theta = new Array2DRowRealMatrix(MatrixUtils.rand(2, 1));
        
        RealMatrix x = new Array2DRowRealMatrix(xTrain);
        
        RealMatrix xbt = x.transpose();
        
        RealMatrix xbtheta = x.preMultiply(theta);
        
        RealMatrix a = xbtheta.subtract(new Array2DRowRealMatrix(yTrain));
                
                
        
        VUtils<Double> view = new VUtils<>();
        
        
        
        for(int i = 0; i < numIterations; i++){
            
        }
        
        return null;
    }
    
  */


    @Override
    public Estimator fit(Double[][] xTrain, Double[] yTrain) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double[] predict(Double[][] xSample) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double score(Double[][] testX, Double[] trueLabelY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double[][] getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double[] getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

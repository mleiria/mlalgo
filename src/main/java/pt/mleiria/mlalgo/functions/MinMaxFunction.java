/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.functions;

/**
 *
 * @author manuel
 */
public class MinMaxFunction implements OneVarFunction<Double[], Double>{
    /**
     * 
     * @param x x[0]=value; x[1]=min;x[2]=max
     * @return 
     */
    @Override
    public Double value(Double[] x) {
        return (x[0] - x[1])/(x[2] - x[1]);
    }
    
    
    
}

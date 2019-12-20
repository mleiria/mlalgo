/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.metrics;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public interface Score<S, T, R> {
    
    R score(S yPred, T yTrue);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.utils;

/**
 * @param <X>
 * @param <Y>
 * @author manuel
 */
public class Tuple2<X, Y> {

    private X x;
    private Y y;

    public Tuple2() {
    }

    /**
     * @param x
     * @param y
     */
    public Tuple2(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }


    @Override
    public String toString() {
        return "{" + x + " : " + y + "}";
    }

}

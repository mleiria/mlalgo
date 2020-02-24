package pt.mleiria.ai;

import pt.mleiria.mlalgo.utils.Tuple2;

public class VacuumCleaner implements Universe {


    private final double[][] value;
    private Tuple2<Integer, Integer> currentPosition;


    /**
     *
     * @param size
     */
    public VacuumCleaner(final int size){
        this.value = new double[size][size];
        this.currentPosition = new Tuple2<>(0,0);
    }

    @Override
    public int getSize() {
        return value.length;
    }

    @Override
    public boolean isFrontier(final int x, final int y) {
        //Is North or South?
        if(x == 0 || x == value.length - 1){
            return true;
        }
        //Is East or West
        if(y == value.length - 1 || y == 0){
            return true;
        }
        return false;
    }

    @Override
    public Tuple2<Integer, Integer> getPosition() {
        return currentPosition;
    }

    @Override
    public void setPosition(final int x, final int y) {
        currentPosition = new Tuple2<>(x, y);
    }

    @Override
    public boolean isCellEmpty(final int x, final int y) {
        return value[x][y] == 0.;
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        return false;
    }
}

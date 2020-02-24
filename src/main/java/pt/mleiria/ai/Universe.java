package pt.mleiria.ai;

import pt.mleiria.mlalgo.utils.Tuple2;

public interface Universe {

    int getSize();

    boolean isFrontier(final int x, final int y);

    Tuple2<Integer, Integer> getPosition();

    void setPosition(final int x, final int y);

    boolean isCellEmpty(final int x, final int y);

    boolean isValidPosition(final int x, final int y);
}

package rx.practice.design.yard.simulation.util.table;

import java.util.List;

public interface Row {
    List<Cell> getCells();
    default int countCells() {
        return getCells().size();
    }
    default int getWidth() {
        return toString().length() -1;
    }
    static interface Cell {
        int getWidth();
    }
}

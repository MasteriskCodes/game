package rx.practice.design.yard.simulation.util.table;

import java.util.List;

/**
 * validateRows() has to be invoked when constructing an instance of Table to validate the rows
 */
public interface Table {
    List<Row> getRows();
    char getBoarderChar();
    default void validateRows() {
        if (getRows() == null || getRows().size() == 0)
            throw new IllegalArgumentException("A table cannot be empty");
        final int firstRowLength = getRows().stream().findAny().get().countCells();
        getRows().forEach(row -> {
            if (row.countCells() != firstRowLength)
                throw new IllegalArgumentException("Each row has to contain the same amount of cells");
        });
    }
    default String getTableBoarder() {
        int boarderLength = getRows().stream().findAny().get().getWidth();
        return TableUtils.getRepeatingChars(getBoarderChar(), boarderLength) + System.lineSeparator();
    }
}

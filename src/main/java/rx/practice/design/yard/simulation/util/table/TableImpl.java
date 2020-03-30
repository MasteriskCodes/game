package rx.practice.design.yard.simulation.util.table;

import java.util.Collections;
import java.util.List;

/**
 * The table, which will be printed to the console
 */
public final class TableImpl implements Table {
    private final List<Row> rows;
    private final char boarderChar;
    public TableImpl(List<Row> rows, char boarderChar) {
        this.rows = Collections.unmodifiableList(rows);
        this.boarderChar = boarderChar;
        validateRows();
    }

    @Override
    public List<Row> getRows() {
        return rows;
    }

    public char getBoarderChar() {
        return boarderChar;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTableBoarder());
        for (Row row : rows)
            stringBuilder.append(row.toString());
        stringBuilder.append(getTableBoarder());
        return stringBuilder.toString();
    }
}

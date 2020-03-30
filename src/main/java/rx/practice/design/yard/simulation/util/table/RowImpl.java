package rx.practice.design.yard.simulation.util.table;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RowImpl implements Row {
        private final List<Row.Cell> cells;
        private final char cellBoarder;
        public RowImpl(List<Row.Cell> cells, char cellBoarder) {
            this.cells = Collections.unmodifiableList(cells);
            this.cellBoarder =cellBoarder;
        }
        @Override
        public String toString() {
            return new StringBuilder()
                    .append(cellBoarder)
                    .append(cells.stream().map(Row.Cell::toString).collect(Collectors.joining(""+cellBoarder)))
                    .append(cellBoarder)
                    .append(System.lineSeparator())
                    .toString();
        }

    @Override
    public List<Row.Cell> getCells() {
        return cells;
    }

    public static final class Cell implements Row.Cell {
            private final int width;
            private final String value;
            private final boolean centered;
            public Cell(int width, boolean centered, String value) {
                this.width = width;
                this.centered = centered;
                this.value = value;
            }
            public static Cell emptyCell(int width) {
                return new Cell(width, true, "");
            }
            @Override
            public String toString() {
                return new StringBuilder().append(getLeftPad(value, centered, width))
                        .append(value)
                        .append(getRightPad(value, centered, width))
                        .toString();
            }
            private String getLeftPad(String value, boolean centered, int width) {
                if (centered) {
                    return getPad((width-value.length())/2);
                }
                else return "";
            }
            private String getRightPad(String value, boolean centered, int width) {
                return getPad(width - getLeftPad(value, centered, width).length() - value.length());
            }
            private String getPad(int width) {
                return TableUtils.getRepeatingChars(' ', width);
            }

        @Override
        public int getWidth() {
            return width;
        }
    }
}

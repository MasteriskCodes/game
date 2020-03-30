package rx.practice.design.yard.simulation.util.table;

import rx.practice.design.yard.Animal;

import java.util.ArrayList;
import java.util.List;

public final class TableUtils {
    private TableUtils() {}
    /**
     * Given a list of animals, get the length of the longest description header
     *
     * @param animals
     * @return
     */
    private static int getLongestDescriptionHeaderLength(List<Animal> animals) {
        return animals.stream()
                .map(Animal::getName)
                .mapToInt(String::length)
                .max()
                .getAsInt();
    }
    static String getRepeatingChars(char ch, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++)
            stringBuilder.append(ch);
        return stringBuilder.toString();
    }
    private static Row getFriendMappingHeaderRow(List<Animal> animals, char cellBoarder) {
        List<Row.Cell> cells = new ArrayList<>();
        int cellWidth = getLongestDescriptionHeaderLength(animals);
        cells.add(RowImpl.Cell.emptyCell(cellWidth));
        for (Animal current : animals)
            cells.add(new RowImpl.Cell(cellWidth, true, current.getName()));
        return new RowImpl(cells, cellBoarder);
    }
    private static Row getFriendMappingRow(Animal animal, List<Animal> animals, char cellBoarder) {
        List<Row.Cell> cells = new ArrayList<>();
        int cellWidth = getLongestDescriptionHeaderLength(animals);
        cells.add(new RowImpl.Cell(cellWidth, true, animal.getName()));
        for (Animal current : animals) {
            if (animal.equals(current))
                cells.add(new RowImpl.Cell(cellWidth, true, "\\"));
            else if (animal.isFriend(current))
                cells.add(new RowImpl.Cell(cellWidth, true, "X"));
            else
                cells.add(RowImpl.Cell.emptyCell(cellWidth));
        }
        return new RowImpl(cells, cellBoarder);
    }
    public static Table getFriendMappingTable(char cellBoarder, List<Animal> animals, char tableBoarder) {
        List<Row> rows = new ArrayList<>();
        rows.add(getFriendMappingHeaderRow(animals, cellBoarder));
        for (Animal current : animals)
            rows.add(getFriendMappingRow(current, animals, cellBoarder));
        return new TableImpl(rows, tableBoarder);
    }
    public static Table getFriendMappingTable(List<Animal> animals) {
        return getFriendMappingTable('|', animals, '-');
    }
}

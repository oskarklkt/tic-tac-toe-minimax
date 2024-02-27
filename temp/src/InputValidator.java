public class InputValidator {
    public static boolean validateCellAvailability(int row, int col, Board board) {
        return board.isCellEmpty(row - 1, col - 1);
    }

    public static boolean validateCoordinatesRange(int row, int col, Board board) {
        return row <= board.getSIZE() && col <= board.getSIZE();
    }

    public static boolean validateCoordinateInput(String input) {
        String regex = "^-?\\d+\\s-?\\d+$";
        return input.matches(regex);
    }

    public static boolean validateInitialState(String input) {
        String regex = "^[XO_]{9}$";
        return input.matches(regex);
    }
}


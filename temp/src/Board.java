public class Board {
    private char[][] board;
    private static final int SIZE = 3;

    public Board(String initialState) {
        this.board = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char c = initialState.charAt(row * 3 + col);
                if (c == '_') {
                    this.board[row][col] = ' ';
                } else {
                    this.board[row][col] = c;
                }
            }
        }
    }

    public int getSIZE() {
        return SIZE;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCellStatus(int row, int col) {
        return board[row][col];
    }

    public void setCellStatus(int row, int col, char symbol) {
        board[row - 1][col - 1] = symbol;
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    public boolean isBoardFullyOccupied() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isCellEmpty(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("---------");
        for (char[] row : board) {
            System.out.println("| " + row[0] + " " + row[1] + " " + row[2] + " |");
        }
        System.out.println("---------");
    }

    public boolean checkWin(char symbol) {
        for (char[] row : board) {
            if (row[0] == row[1] && row[1] == row[2] && row[2] == symbol) {
                return true;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == symbol &&  board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        return board[1][1] == symbol &&
                ((board[0][0] == symbol && board[2][2] == symbol) ||
                        (board[0][2] == symbol && board[2][0] == symbol));
    }

    public boolean checkDraw() {
        return isBoardFullyOccupied() && !checkWin('X') && !checkWin('O');
    }


}
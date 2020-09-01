public class SudokuBoard {
    private int[][] board;

    //constructor for an empty board
    public SudokuBoard() {
        this.board = new int[9][9];
    }

    //constructor for a filled board
    public SudokuBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard () {
        return this.board;
    }

    public void updateBoard(String row, String col, String input) throws Exception, NumberFormatException {
        int rowNum = Integer.parseInt(row);
        int colNum = Integer.parseInt(col);
        int val = Integer.parseInt(input);
        this.updateBoard(rowNum, colNum, val);
    }

    private void updateBoard(int row, int col, int val) throws Exception {
        if(val < 0 || val > 9) {
            throw new Exception("Invalid Sudoku Input");
        }
        this.board[row][col] = val;
    }
}

public class Sudoku {
    private SudokuBoard board;
    private SudokuSolver solver;

    public Sudoku() {
        this.board = new SudokuBoard();
        this.solver = new SudokuSolver(board.getBoard());
    }

    private void handleException(Exception e) {
        System.out.println(e.toString());
        //e.printStackTrace();
        System.exit(-1);
    }

    public static void main(String args[]) {
        Sudoku sudoku = null;
        try {
            sudoku = new Sudoku();
            boolean isSolved = sudoku.solver.solveSudoku();
            System.out.println("Sudoku Result: " + isSolved);
            System.out.println(sudoku.solver.toString());
        }
        catch(NumberFormatException nfe) {
            System.out.println("Sudoku board does not accept strings!");
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}

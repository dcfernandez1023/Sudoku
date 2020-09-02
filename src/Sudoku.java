import java.util.Scanner;

public class Sudoku {
    private SudokuSolver solver;
    private SudokuBoard board;

    public Sudoku() {
        this.board = new SudokuBoard();
        this.solver = new SudokuSolver(this.board);
    }

    private static void handleException(Exception e) {
        System.out.println("Something went wrong!  Error message: " + e.getMessage());
        System.out.println("~~ Stack Trace ~~");
        e.printStackTrace();
        System.exit(-1);
    }

    public static void main(String args[]) {
        Sudoku sudoku = null;
        try {
            sudoku = new Sudoku();
            Scanner scanner = new Scanner(System.in);
            System.out.println("~~ SUDOKU BOARD SOLVER ~~");
            System.out.println("Enter 'e' to generate a solved Sudoku board from scratch, or enter any key to manually create your own Sudoku board.");
            String option = scanner.nextLine();
            if(option.equals("e")) {
                boolean isSolved = sudoku.solver.solveSudoku();
                System.out.println("Sudoku Board Solved: " + isSolved);
                System.out.println(sudoku.board.toString());
                System.exit(0);
            }
            sudoku.board.customizeBoard();
            boolean isSolved = sudoku.solver.solveSudoku();
            System.out.println("Sudoku Board Solved: " + isSolved);
            System.out.println(sudoku.board.toString());
            System.exit(0);
        }
        catch(Exception e) {
            handleException(e);
        }
    }
}

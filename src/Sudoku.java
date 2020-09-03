import java.util.Scanner;

public class Sudoku {
    /* data members:
        * solver - SudokuSolver object with instance methods to solve Sudoku
        * board - SudokuBoard object as a wrapper for a 9x9 Sudoku board
    */
    private SudokuSolver solver;
    private SudokuBoard board;

    /*
        * params: none
        * description: constructs Sudoku object and sets data members
        * return: none
    */
    public Sudoku() {
        this.board = new SudokuBoard();
        this.solver = new SudokuSolver(this.board);
    }

    /*
         * params:
            * Exception e
         * description: handles any exception thrown in the main method, prints the exception, and traces its stack
         * return: none
     */
    private static void handleException(Exception e) {
        System.out.println("Something went wrong!  Error message: " + e.getMessage());
        System.out.println("~~ Stack Trace ~~");
        e.printStackTrace();
        System.exit(-1);
    }

    public static void main(String args[]) {
        //initialize
        Sudoku sudoku = null;
        try {
            //instantiate
            sudoku = new Sudoku();
            Scanner scanner = new Scanner(System.in);

            //print some welcome messages and instructions
            System.out.println("~~ SUDOKU BOARD SOLVER ~~");
            System.out.println("Enter 'e' to generate a solved Sudoku board from scratch, or enter any key to manually create your own Sudoku board.");

            //get initial inputs that determine the course of action for the algorithm
            String option = scanner.nextLine();
            if(option.equals("e")) {
                //generates Sudoku board from scratch (from an empty board)
                boolean isSolved = sudoku.solver.solveSudoku();
                sudoku.solver.printResults(isSolved);
                System.exit(0);
            }
            //generates Sudoku board based on user inputs
            sudoku.board.customizeBoard();
            boolean isSolved = sudoku.solver.solveSudoku();
            sudoku.solver.printResults(isSolved);
            System.exit(0);
        }
        catch(Exception e) {
            //handle exceptions thrown
            handleException(e);
        }
    }
}

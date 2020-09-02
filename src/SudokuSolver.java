/*
ALGORITHM BEING IMPLEMENTED:
*place number (1-9 starting from 1) in cell
*if placement is valid (valid in row, col, and box)
    *then proceed to next cell (recursively call function)
*if placement is not valid
    *then leave the current cell as 0, backtrack to the previous cell -- repeat
*/

public class SudokuSolver {
    private SudokuBoard sudokuBoard;
    private int[][] board;
    private int numBackTracks;

    public SudokuSolver(SudokuBoard board) {
        this.sudokuBoard = board;
        this.board = this.sudokuBoard.getBoard();
        this.numBackTracks = 0;
    }
    /*
        * params: none
        * description: will validate sudoku board by trial and error through backtracking -- this function is recursive
        * return: boolean -- true if sudoku board is valid; false if board is not valid
    */
    public boolean solveSudoku() throws Exception {
        int rowToFill;
        int colToFill;
        int[] cellCheck = this.checkCells();

        //base case
        if(cellCheck[0] == 1) {
            return this.validateBoard();
        }

        //invalid board check (if board cannot be solved with the inputs the user gave)
        if(cellCheck[0] == -1) {
            return false;
        }

        //sets the row-col position to add number to next
        rowToFill = cellCheck[1];
        colToFill = cellCheck[2];
        for(int i = 1; i <= this.board.length; i++) {
            if(this.isValidPlacement(rowToFill, colToFill, i, 0)) {
                //progress towards base case
                this.sudokuBoard.updateBoard(rowToFill, colToFill, i);
                if(this.solveSudoku()) {
                    return true;
                }
                //backtracking step -- this will execute if all values (1-9) have been tried but failed
                this.sudokuBoard.updateBoard(rowToFill, colToFill, 0);
                this.numBackTracks++;
            }
        }
        return false;
    }

    public void printResults(boolean solved) {
        System.out.println("---------------------");
        System.out.println("~~ SUDOKU SOLVER RESULTS ~~");
        System.out.println("");
        System.out.println("Sudoku Board Solved: " + solved);
        System.out.println("Number of back-tracks used: " + this.numBackTracks);
        System.out.println("");
        System.out.println(this.sudokuBoard.toString());
    }

    /*
        * params:
             * int rowNum -- row number (0-8)
             * int colNum -- col number (0-8)
        * description: checks if all cells contain a number and returns an int[] with 0 (false) and the positions of where the next
                       number should be filled; an int[] with 1 (true) if nothing else needs to be filled; an int[] with -1 (invalid board)
                       if the inputs that the user entered on the board make it impossible to complete the board
        * return: int[]
    */
    private int[] checkCells() throws Exception {
        for(int i = 0; i < this.board.length; i++) {
            if(!this.validateBoard()) {
                return new int[] {-1};
            }
            int[] row = this.board[i];
            for(int x = 0; x < row.length; x++) {
                if(row[x] == 0) {
                    // 0 = false -- cells have not yet been filled 
                    // i = next row to check
                    // x = next col to check
                    return new int[] {0, i, x};
                }
            }
        }
        // 1 = true -- all cells have been filled 
        return new int[] {1};
    }

    /*
        * params:
            * int rowNum -- row number (0-8)
            * int colNum -- col number (0-8)
            * int val -- value (1-9) to be added at board[row][col]
            * int countMax -- the amount of times you want a number to be counted within its corresponding row, col, & 3x3 box to determine if its placement is valid
        * description: determines if value can be placed at a given row col position
        * return: boolean -- true if placement on board is valid; false if not
    */
    private boolean isValidPlacement(int rowNum, int colNum, int val, int countMax) throws Exception {
        if(val == 0) {
            return true;
        }
        if(val < 0 || val > 9) {
            throw new Exception("Invalid Sudoku Input: " + val + " " + "cannot be placed on the board.");
        }
        int[] row;
        //determine if placement in this row is valid
        row = this.board[rowNum];
        for(int i = 0; i < row.length; i++) {
            int count = 0;
            for(int x = 0; x < row.length; x++) {
                if(val == row[x]) {
                    count++;
                }
            }
            if(count > countMax)
            {
                return false;
            }
        }

        //determine if placement in this col is valid
        int count = 0;
        for(int i = 0; i < this.board.length; i++) {
            row = this.board[i];
            if(row[colNum] == val) {
                count++;
            }
        }
        if(count > countMax) {
            return false;
        }

        //determine if placement in this 3x3 box is valid
        count = 0;
        //for rows 0,1,2
        if(0 <= rowNum && rowNum <= 2) {
            for(int i = 0; i < 3; i++) {
                row = this.board[i];
                //for cols 0,1,2
                if(0 <= colNum && colNum <= 2) {
                    for(int n = 0; n < 3; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
                //for cols 3,4,5
                else if(3 <= colNum && colNum <= 5) {
                    for(int n = 3; n < 6; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
                //for cols 6,7,8
                else {
                    for(int n = 6; n < 9; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
            }
        }
        //for rows 3,4,5
        else if(3 <= rowNum && rowNum <= 5) {
            for(int i = 3; i < 6; i++) {
                row = this.board[i];
                //for cols 0,1,2
                if(0 <= colNum && colNum <= 2) {
                    for(int n = 0; n < 3; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
                //for cols 3,4,5
                else if(3 <= colNum && colNum <= 5) {
                    for(int n = 3; n < 6; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
                //for cols 6,7,8
                else {
                    for(int n = 6; n < 9; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
            }
        }
        //for rows 6,7,8
        else {
            for(int i = 6; i < 9; i++) {
                row = this.board[i];
                //for cols 0,1,2
                if(0 <= colNum && colNum <= 2) {
                    for(int n = 0; n < 3; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
                //for cols 3,4,5
                else if(3 <= colNum && colNum <= 5) {
                    for(int n = 3; n < 6; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
                //for cols 6,7,8
                else {
                    for(int n = 6; n < 9; n++) {
                        if(val == row[n]) {
                            count++;
                        }
                    }
                }
            }
        }
        if(count > countMax) {
            return false;
        }
        return true;
    }


    private boolean validateBoard() throws Exception {
        for(int n = 1; n <= this.board.length; n++) {
            for (int i = 0; i < this.board.length; i++) {
                int[] row = this.board[i];
                for (int x = 0; x < row.length; x++) {
                    if (!this.isValidPlacement(i, x, row[x], 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

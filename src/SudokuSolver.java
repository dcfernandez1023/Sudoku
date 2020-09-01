/*
ALGORITHM BEING IMPLEMENTED:
*place number (1-9 starting from 1) in cell
*if placement is valid (valid in row, col, and box)
    *then proceed to next cell (proceed to next iteration of loop)
*if placement is not valid
    *then leave the current cell as 0, backtrack to the previous cell -- repeat
*/

public class SudokuSolver {
    private int[][] board;
    //private int[] constantColPositions;
    //private int[] constantRowPositions;

    public SudokuSolver(int[][] sudokuBoard) {
        this.board = sudokuBoard;
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
        if(cellCheck[0] == 0) {
            return true;
        }
        rowToFill = cellCheck[1];
        colToFill = cellCheck[2];
        for(int i = 1; i <= this.board.length; i++) {
            if(this.isValidPlacement(rowToFill, colToFill, i)) {
                this.makePlacement(rowToFill, colToFill, i);
                //progress towards base case
                if(solveSudoku()) {
                    return true;
                }
                //backtracking step -- this will execute if all values (1-9) have been tried within this for loop but failed
                this.makePlacement(rowToFill, colToFill, 0);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String strBoard = "";
        for(int i = 0; i < this.board.length; i++) {
            //loop thru row
            int[] row = this.board[i];
            for(int x = 0; x < row.length; x++) {
                //loop thru col
                int val = row[x];
                if(x == row.length - 1) {
                    strBoard = strBoard + val + "\n";
                    if(i == 2 || i == 5) {
                        for(int n = 0; n < 60; n++) {
                            strBoard = strBoard + "-";
                        }
                        strBoard = strBoard + "\n";
                    }
                }
                else {
                    if(x == 3 || x == 6) {
                        strBoard = strBoard + "|    ";
                    }
                    strBoard = strBoard + val + "     ";
                }
            }
        }
        return strBoard;
    }

    /*
        * params:
             * int rowNum -- row number (0-8)
             * int colNum -- col number (0-8)
        * description: checks if all cells contain a number and returns an int[] with the position of where the next
                        number should be filled; an int[] with 0 if nothing needs to be filled
        * return: int[]
    */
    private int[] checkCells() {
        for(int i = 0; i < this.board.length; i++) {
            int[] row = this.board[i];
            for(int x = 0; x < row.length; x++) {
                if(row[x] == 0) {
                    return new int[] {1, i, x};
                }
            }
        }
        return new int[] {0};
    }

    /*
        * params:
            * int rowNum -- row number (0-8)
            * int colNum -- col number (0-8)
            * int val -- value (1-9) to be added at board[row][col]
        * description: determines if value can be placed at a given row col position
        * return: boolean -- true if placement on board is valid; false if not
    */
    private boolean isValidPlacement(int rowNum, int colNum, int val) throws Exception {
        if(val < 1 || val > 9) {
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
            if(count > 0)
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
        if(count > 0) {
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
        if(count > 0) {
            return false;
        }
        return true;
    }

    /*
        * params:
             * int rowNum -- row number (0-8)
             * int colNum -- col number (0-8)
             * int val -- value (1-9) to be added at board[row][col]
         * description: places value at a given row col position
         * return: none
     */
    private void makePlacement(int rowNum, int colNum, int val) throws Exception {
        if(val < 0 || val > 9) {
            throw new Exception("Invalid Sudoku Input");
        }
        this.board[rowNum][colNum] = val;
    }
}

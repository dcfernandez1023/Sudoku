import java.util.Scanner;

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

    public void customizeBoard() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while(i < this.board.length) {
            int[] row = this.board[i];
            int x = 0;
            while(x < row.length) {
                System.out.println("Enter a number (0-9) for Row " + i + ", Col " + x + ":");
                String input = scanner.nextLine();
                if(input.equals("-1")) {
                    return;
                }
                if(this.isInt(input)) {
                    int val = Integer.parseInt(input);
                    if(0 <= val && val <= 9) {
                        this.updateBoard(i, x, val);
                        x++;
                        System.out.println("This is what your current Sudoku board looks like:");
                        System.out.println(this.toString());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("");
                    }
                    else {
                        System.out.println(val + " is not a valid Sudoku input. Please enter a number from 0-9.");
                        System.out.println("");
                    }
                }
                else {
                    System.out.println(input + " is not a valid Sudoku input. Please enter a number from 0-9.");
                    System.out.println("");
                }
            }
            i++;
        }

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
         * int val -- value (1-9) to be added at board[row][col]
     * description: places value at a given row col position
     * return: none
     */
    public void updateBoard(int row, int col, int val) {
        this.board[row][col] = val;
    }

    private boolean isInt(String input) {
        if(input == null) {
            return false;
        }
        try {
            int val = Integer.parseInt(input);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

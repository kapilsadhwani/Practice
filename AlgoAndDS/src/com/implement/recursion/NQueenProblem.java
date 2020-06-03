package com.implement.recursion;
/* Java program to solve N Queen Problem using 
   backtracking */
public class NQueenProblem { 
    final int N = 4; 
  
    /* A utility function to print solution */
    void printSolution(int board[][]) { 
        for (int i = 0; i < N; i++) { 
            for (int j = 0; j < N; j++) 
                System.out.print(" " + board[i][j] 
                                 + " "); 
            System.out.println(); 
        } 
    } 
  
    /* A utility function to check if a queen can 
       be placed on board[row][col]. Note that this 
       function is called when "row" queens are already 
       placed in columns from 0 to row-1. So we need 
       to check only top side for attacking queens */
    boolean canPlace(int board[][], int row, int col) { 
        /* Check this col on upper side */
        for (int r = 0; r < row; r++) 
            if (board[r][col] == 1) 
                return false; 
  
        /* Check left diagonal on upper side */
        for (int r=row, c=col; r>=0 && c>=0; r--, c--) 
            if (board[r][c] == 1) 
                return false; 
  
        /* Check right diagonal on upper side */
        for (int r=row, c=col; r>=0 && c<N; r--, c++) 
            if (board[r][c] == 1) 
                return false; 
  
        return true; 
    } 
  
    /* A recursive utility function to solve N Queen problem */
    boolean solveNQUtil(int board[][], int row) { 
        /* base case: If all queens are placed 
           then return true */
        if (row == N) 
            return true; 
  
        /* Consider this row and try placing 
           this queen in all columns one by one */
        for (int col = 0; col < N; col++) { 
            /* Check if the queen can be placed on 
               board[row][i] */
            if (canPlace(board, row, col)) { 
                /* Place this queen in board[row][i] */
                board[row][col] = 1; 
  
                /* recur to place rest of the queens */
                if (solveNQUtil(board, row + 1)) 
                    return true; 
  
                /* If placing queen in board[row][i] 
                   doesn't lead to a solution then 
                   remove queen from board[row][i] */
                board[row][col] = 0; // BACKTRACK 
            } 
        } 
  
        /* If the queen can not be placed in any col in 
           this row 'row', then return false */
        return false; 
    } 
  
    /* This function solves the N Queen problem using 
       Backtracking.  It mainly uses solveNQUtil () to 
       solve the problem. It returns false if queens 
       cannot be placed, otherwise, return true and 
       prints placement of queens in the form of 1s. 
       Please note that there may be more than one 
       solutions, this function prints one of the 
       feasible solutions.*/
    void solveNQ() { 
        int board[][] = new int[N][N]; 
        
        /*for (int i = 0; i < N; i++) { 
            for (int j = 0; j < N; j++) {
            	board[i][j] = 0;
            }
        }*/
  
        if (!solveNQUtil(board, 0)){ 
            System.out.print("Solution does not exist"); 
        }else{
        	printSolution(board); 
        }
    } 
  
    // driver program to test above function 
    public static void main(String args[]) { 
        NQueenProblem Queen = new NQueenProblem(); 
        Queen.solveNQ(); 
    } 
} 
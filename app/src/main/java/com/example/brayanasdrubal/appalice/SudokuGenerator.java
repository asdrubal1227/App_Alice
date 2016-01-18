package com.example.brayanasdrubal.appalice;

import java.util.ArrayList;
import java.util.Random;

public class SudokuGenerator {
	private static SudokuGenerator instance;
	
	private Random rand = new Random();
	
	private SudokuGenerator(){}
	
	/**
	 * Creates an instance of the SudokuGenerator class if it's not exist
	 * @return a created instance
	 */
	public static SudokuGenerator getInstance(){
		if( instance == null ){
			instance = new SudokuGenerator();
		}
		return instance;
	}
	
	/**
	 * Generates a 9x9 grid of sudoku
	 * @return a generated sudoku grid
	 */
	public int[][] generateGrid(){
		int[][] Sudoku = new int[9][9]; 
		
		ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>(); 
		
		int currentPos = 0; 
		
		clearGrid(Sudoku , Available);
		
		while( currentPos < 81 ){
			
			if( Available.get(currentPos).size() != 0 ){
				int i = rand.nextInt(Available.get(currentPos).size());
				int number = Available.get(currentPos).get(i);
				
				if( !checkConflict(Sudoku, currentPos , number)){
					int xPos = currentPos % 9;
					int yPos = currentPos / 9;
					
					Sudoku[xPos][yPos] = number;
					
					Available.get(currentPos).remove(i);
					
					currentPos++;
				}else{
					Available.get(currentPos).remove(i);
				}
				
			}else{
				for( int i = 1 ; i <= 9 ; i++ ){
					Available.get(currentPos).add(i);
				}
				currentPos--;
			}
		}
		
		
		return Sudoku;
	}
	
	/**
	 * Clears the SudokuArray and restores the Available numbers arraylist
	 * @param Sudoku
	 * @param Available
	 */
	private void clearGrid(int [][] Sudoku , ArrayList<ArrayList<Integer>> Available){
		Available.clear();
		
		for( int y =  0; y < 9 ; y++ ){
			for( int x = 0 ; x < 9 ; x++ ){
				Sudoku[x][y] = -1;
			}
		}

		
		for( int x = 0 ; x < 81 ; x++ ){
			Available.add(new ArrayList<Integer>());
			for( int i = 1 ; i <= 9 ; i++){
				Available.get(x).add(i);
			}
		}
	}
	
	/**
	 * Checks for conflicts horizontally , vertically and in a region.
	 * @param Sudoku
	 * @param currentPos
	 * @param number
	 * @return true if there is a conflict, false otherwise
	 */
	private boolean checkConflict( int[][] Sudoku , int currentPos , final int number){
		int xPos = currentPos % 9;
		int yPos = currentPos / 9;
		
		if( checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number) ){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks for conflicts horizontally
	 * @param Sudoku
	 * @param xPos
	 * @param yPos
	 * @param number
	 * @return true if there is a conflict, false otherwise
	 */
	private boolean checkHorizontalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
		for( int x = xPos - 1; x >= 0 ; x-- ){
			if( number == Sudoku[x][yPos]){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks for conflicts vertically
	 * @param Sudoku
	 * @param xPos
	 * @param yPos
	 * @param number
	 * @return true if there is a conflict, false otherwise
	 */
	private boolean checkVerticalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
		for( int y = yPos - 1; y >= 0 ; y-- ){
			if( number == Sudoku[xPos][y] ){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks for conflicts in the region
	 * @param Sudoku
	 * @param xPos
	 * @param yPos
	 * @param number
	 * @return true if there is a conflict, false otherwise
	 */
	private boolean checkRegionConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
		int xRegion = xPos / 3;
		int yRegion = yPos / 3;
		
		for( int x = xRegion * 3 ; x < xRegion * 3 + 3 ; x++ ){
			for( int y = yRegion * 3 ; y < yRegion * 3 + 3 ; y++ ){
				if( ( x != xPos || y != yPos ) && number == Sudoku[x][y] ){
					return true;
				}
			}
		}
		
		return false;
	}
}

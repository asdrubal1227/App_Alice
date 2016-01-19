package com.example.brayanasdrubal.appalice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class sudoku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sudoku);

        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();
        GameEngine.getInstance().setSudoku(Sudoku);

        printSudoku(Sudoku);
    }

    private void printSudoku(int Sudoku[][]) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(Sudoku[x][y] + "|");
            }
            System.out.println();
        }
    }

}


package com.example.brayanasdrubal.appalice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class SudokuGridView extends GridView{
	
	private final Context context;
	
	public SudokuGridView(final Context context , AttributeSet attrs) {
		super(context , attrs);
		
		this.context = context;
		
		SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);
		
		setAdapter(gridViewAdapter);
		
		setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				int x = position % 9;
				int y = position / 9;
				
				Toast.makeText(context, "Selected item - x: " + x + " y: " + y , Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
}

package com.example.brayanasdrubal.appalice.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.brayanasdrubal.appalice.GameEngine;
import com.example.brayanasdrubal.appalice.R;

public class SudokuGridViewAdapter extends BaseAdapter{
	
	private Context context;
	
	public SudokuGridViewAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return 81;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		if( v == null ){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			v = inflater.inflate(R.layout.cell, parent , false);
		}
		
		int x = position % 9;
		int y = position / 9;
		
		SudokuCell cell = (SudokuCell)v;
		cell.setNumber(GameEngine.getInstance().getSudoku()[x][y]);

		return v;
	}

}

package com.example.brayanasdrubal.appalice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadialMenuWidget pieMenu;

    public LinearLayout layout,layout2;
    public Activity activity = this;
    public int xLayoutSize,yLayoutSize;
    public RadialMenuItem menuItem, menuCloseItem, menuExpandItem,menuItem2;
    public RadialMenuItem firstChildItem, secondChildItem, thirdChildItem;
    private List<RadialMenuItem> children = new ArrayList<RadialMenuItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        refeshPrefs();

        layout = (LinearLayout) this.findViewById(R.id.menu);
        layout2 = (LinearLayout) this.findViewById(R.id.este);

        xLayoutSize = layout.getWidth();

        yLayoutSize = layout.getHeight();
        pieMenu = new RadialMenuWidget(getBaseContext());

        menuCloseItem = new RadialMenuItem("Close", null);
        menuCloseItem.setDisplayIcon(android.R.drawable.ic_media_play);

        menuItem = new RadialMenuItem("Evaluate","");
        menuItem2 = new RadialMenuItem("Juegos","");

        menuExpandItem = new RadialMenuItem("Memorias","");

        menuExpandItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                startActivity(new Intent(MainActivity.this, Memorias.class));
                pieMenu.dismiss();
            }
        });
        menuItem2.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                startActivity(new Intent(MainActivity.this, Juegos.class));
                pieMenu.dismiss();
            }
        });

        menuCloseItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                //menuLayout.removeAllViews();
                pieMenu.dismiss();
            }
        });

        menuItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                startActivity(new Intent(MainActivity.this, Evaluate.class));
                pieMenu.dismiss();
            }
        });

        //pieMenu.setDismissOnOutsideClick(true, menuLayout);
        pieMenu.setAnimationSpeed(0L);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = 540;
        pieMenu.setCenterLocation(225, 225);
        pieMenu.setIconSize(15, 30);
        pieMenu.setTextSize(13);
        pieMenu.setOutlineColor(Color.alpha(0x000080), 225);
        pieMenu.setInnerRingColor(0xAA66CC, 10);
        pieMenu.setOuterRingColor(0x000000, 100);
        pieMenu.setSelectedColor(0x000000, 100);
        pieMenu.setDisabledColor(0x000000, 100);
        pieMenu.setInnerRingRadius(width / 20 * 3, width / 18 * 5);
        pieMenu.setOuterRingRadius(width / 3, width / 2);
        pieMenu.setRotation(175);
        pieMenu.setRotationY(180);
        //pieMenu.setCenterCircle();
        //pieMenu.
        //pieMenu.setHeader("Test Menu", 20);
        pieMenu.setCenterCircle(menuCloseItem);
        pieMenu.setCenterCircleRadius(50);
       /* DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        pieMenu.setCenterLocation(width/2, height/2);*/

        pieMenu.addMenuEntry(new ArrayList<RadialMenuItem>() {{
            add(menuItem);
            add(menuExpandItem);
            add(menuItem2);
        }});

        //pieMenu.addMenuEntry(menuItem);
        //pieMenu.addMenuEntry(menuExpandItem);
        layout.addView(pieMenu,width/6*5,width/6*5);
        Toast.makeText(MainActivity.this, Integer.toString(width), Toast.LENGTH_SHORT).show();
		/*Button testButton = (Button) this.findViewById(R.id.radial_menu_btn);
		testButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //menuLayout.addView(pieMenu);
                pieMenu.show(v);
            }
        });*/
    }
    public void onClick_juegos (View v){
        Toast.makeText(MainActivity.this, "Juegos", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        Intent i = new Intent(MainActivity.this, Juegos.class);
        startActivity(i);

    }
    public void onClick_memo (View v){
        Toast.makeText(MainActivity.this, "Memorias", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        Intent i = new Intent(MainActivity.this, Memorias.class);
        startActivity(i);

    }
    public void onClick_evalu (View v){
        Toast.makeText(MainActivity.this, "Evaluate", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        Intent i = new Intent(MainActivity.this, Evaluate.class);
        startActivity(i);

    }
   /* public void onClick (View v){
        Toast.makeText(MainActivity.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        savePrefs(v);
        Intent i = new Intent(MainActivity.this, info_cuidador.class);
        startActivity(i);
        finish();

    }*/

   /* public void savePrefs(View view){
        EditText campo= (EditText) findViewById(R.id.editText);
        String campoStr= campo.getText().toString();
        cuenta.editor.putString("nombre", campoStr);
        cuenta.editor.clear(); //Limpia todas las preferencias
        //editor.remove("nombre");
        cuenta.editor.commit();
        refeshPrefs();
    }*/

    public void refeshPrefs(){


        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView info= (TextView) findViewById(R.id.textView);
        String dato= prefs.getString("nombre","Hola");
        info.setText("Hola "+dato);

    }
}

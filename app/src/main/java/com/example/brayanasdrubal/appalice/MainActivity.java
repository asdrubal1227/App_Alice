package com.example.brayanasdrubal.appalice;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    public  static int regreso=0;
    public YouTubePlayerView youtube;
    public YouTubePlayer.OnInitializedListener onInitializedListener;

    private RadialMenuWidget pieMenu;

    public LinearLayout layout, layout2;
    public Activity activity = this;
    public int xLayoutSize, yLayoutSize;
    public RadialMenuItem menuItem, menuCloseItem, menuExpandItem, menuItem2;
    public RadialMenuItem firstChildItem, secondChildItem, thirdChildItem;
    private List<RadialMenuItem> children = new ArrayList<RadialMenuItem>();

    public Intent callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        refeshPrefs();
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
        if (regreso==0) {
            cancel();
            start();
            removeNotification();
        }else {
            removeNotification();
            regreso=0;
        }

        layout = (LinearLayout) this.findViewById(R.id.menu);
        layout2 = (LinearLayout) this.findViewById(R.id.este);

        xLayoutSize = layout.getWidth();

        yLayoutSize = layout.getHeight();
        pieMenu = new RadialMenuWidget(getBaseContext());

        menuCloseItem = new RadialMenuItem("Close", null);
        menuCloseItem.setDisplayIcon(android.R.drawable.ic_media_play);

        menuItem = new RadialMenuItem("Evaluate", "");
        menuItem2 = new RadialMenuItem("Juegos", "");

        menuExpandItem = new RadialMenuItem("Memorias", "");

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
                startActivity(new Intent(MainActivity.this, video.class));
                pieMenu.dismiss();

                /*startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=CkDWixxfgeQ")));
                pieMenu.dismiss();//*/
            }
        });

        menuItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {

                startActivity(new Intent(MainActivity.this, Evaluate.class));
                pieMenu.dismiss();//*/
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
        layout.addView(pieMenu, width / 6 * 5, width / 6 * 5);
        Toast.makeText(MainActivity.this, "App Alice", Toast.LENGTH_SHORT).show();
		/*Button testButton = (Button) this.findViewById(R.id.radial_menu_btn);
		testButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //menuLayout.addView(pieMenu);
                pieMenu.show(v);
            }
        });*/
    }

    public void cal(View v) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.YEAR, 2013);

        cal.set(Calendar.HOUR_OF_DAY, 22);
        cal.set(Calendar.MINUTE, 45);

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis() + 60 * 60 * 1000);

        intent.putExtra(CalendarContract.Events.ALL_DAY, false);
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY");
        intent.putExtra(CalendarContract.Events.TITLE, "Pastilla o evento");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Descripción");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Lugar");

        activity.startActivity(intent);

    }

    public void call(View v) {

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String num = prefs.getString("num", "0911");
        try {
            Uri numero = Uri.parse("tel:" + num);
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(numero);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("dialing-example", "Call failed", activityException);
        }
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
        TextView info= (TextView) findViewById(R.id.textView);
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String dato= prefs.getString("nombre", "?");
        info.setText("Hola " + dato);

    }


    public void cs(View v){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt("entro",0);
        editor.commit();
        Intent i = new Intent(MainActivity.this, Interfaz.class);
        startActivity(i);
        finish();
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 5;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    public void map(View view) {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }// Remove notification
    private void removeNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(5);
    }

}

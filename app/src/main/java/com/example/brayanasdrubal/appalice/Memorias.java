package com.example.brayanasdrubal.appalice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Memorias extends AppCompatActivity {
    private static int SELECT_PICTURE = 2;
    private int veri = 0;
    public static String tx1 = "", tx2 = "";
    public Uri Uri1 = null, Uri2 = null;
    public static Bitmap bitmap = null;
    public static Bitmap bitmap2 = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_memorias);

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        Uri1 = Uri.parse(prefs.getString("uri1", ""));
        Uri2 = Uri.parse(prefs.getString("uri2", ""));
        tx1 = prefs.getString("t1", "");
        tx2 = prefs.getString("t2", "");
        if (Uri1 != null) {
            InputStream is;
            try {
                is = getContentResolver().openInputStream(Uri1);
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                ImageView iv = (ImageView) findViewById(R.id.btnPic);
                iv.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
            }
        }
        if (Uri2 != null) {
            InputStream is;
            try {
                is = getContentResolver().openInputStream(Uri2);
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap2 = BitmapFactory.decodeStream(bis);
                ImageView iv = (ImageView) findViewById(R.id.btnPic2);
                iv.setImageBitmap(bitmap2);
            } catch (FileNotFoundException e) {
            }
        }
        if (tx1 != "" && tx2 != "") {
            confirma();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void click(View v) {
        veri = 1;
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        int code = 1;
        startActivityForResult(intent, code);
    }

    public void confirma(View v) {
        EditText e1 = (EditText) findViewById(R.id.e1);
        String ed1 = e1.getText().toString();
        TextView t1 = (TextView) findViewById(R.id.t1);
        t1.setText(ed1);
        EditText e2 = (EditText) findViewById(R.id.e2);
        String ed2 = e2.getText().toString();
        TextView t2 = (TextView) findViewById(R.id.t2);
        t2.setText(ed2);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        Button b = (Button) findViewById(R.id.bo);
        b.setVisibility(View.GONE);
        LinearLayout i = (LinearLayout) findViewById(R.id.l1);
        i.setClickable(false);
        LinearLayout i2 = (LinearLayout) findViewById(R.id.l2);
        i2.setClickable(false);

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("t1", ed1);
        editor.putString("t2", ed2);
        editor.commit();
    }

    public void confirma() {
        EditText e1 = (EditText) findViewById(R.id.e1);
        TextView t1 = (TextView) findViewById(R.id.t1);
        EditText e2 = (EditText) findViewById(R.id.e2);
        TextView t2 = (TextView) findViewById(R.id.t2);
        t1.setText(tx1);
        t2.setText(tx2);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        Button b = (Button) findViewById(R.id.bo);
        b.setVisibility(View.GONE);
        LinearLayout i = (LinearLayout) findViewById(R.id.l1);
        i.setClickable(false);
        LinearLayout i2 = (LinearLayout) findViewById(R.id.l2);
        i2.setClickable(false);
    }

    public void click2(View v) {
        veri = 2;
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        int code = 2;
        startActivityForResult(intent, code);
    }

    public void click3(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        int code = SELECT_PICTURE;
        startActivityForResult(intent, code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        // editor.clear(); //Limpia todas las preferencias
        //editor.remove("nombre");
        InputStream is;
        if (requestCode == 1) {
            Uri1 = data.getData();
            editor.putString("uri1", Uri1.toString());
            try {
                is = getContentResolver().openInputStream(Uri1);
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                ImageView iv = (ImageView) findViewById(R.id.btnPic);
                iv.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
            }
        } else if (requestCode == 2) {
            Uri2 = data.getData();
            editor.putString("uri2", Uri2.toString());
            try {
                is = getContentResolver().openInputStream(Uri2);
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap2 = BitmapFactory.decodeStream(bis);
                ImageView iv = (ImageView) findViewById(R.id.btnPic2);
                iv.setImageBitmap(bitmap2);
            } catch (FileNotFoundException e) {
            }

        }
        editor.commit();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Memorias Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.brayanasdrubal.appalice/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Memorias Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.brayanasdrubal.appalice/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            MainActivity.regreso=1;
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
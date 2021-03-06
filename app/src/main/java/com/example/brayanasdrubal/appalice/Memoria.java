package com.example.brayanasdrubal.appalice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class Memoria extends AppCompatActivity implements Runnable {

    private TextView lblPuntaje;
    private TextView lblFallas;

    public static int puntaje; //para aumentar el puntaje
    public static int fallas;


    private ImageButton img1;
    private ImageButton img2;
    private ImageButton img3;
    private ImageButton img4;
    private ImageButton img5;
    private ImageButton img6;
    private ImageButton img7;
    private ImageButton img8;
    private ImageButton img9;
    private ImageButton img10;
    private ImageButton img11;
    private ImageButton img12;
    private ImageButton resp;


    private Button botonSalir;
    private Button botonReiniciar;


    private int []valores={1,2,3,4,5,6,1,2,3,4,5,6}; //son valores para idetificar con las cartas
    private ImageButton imgBotones[];
    private int valorSeleccionado=-1; //variable para saber el ultimo valor escogido
    private int valorBorrar=0; //es para grabar que valor tiene que girar de nuevo cunado se equivoque

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_memoria);
        puntaje=0;
        fallas=0;
        iniciarCartas();
        imgBotones=new ImageButton[]{img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12}; //arrglo de todos las cartas para poder utilizar
        desordenarCartas();
        botonSalir=(Button)findViewById(R.id.btnSalir);
        botonReiniciar=(Button)findViewById(R.id.btnReiniciar);
        botonSalir.setVisibility(View.GONE);
        botonReiniciar.setVisibility(View.GONE);

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.exit(0);
                startActivity(new  Intent(Memoria.this, Juegos.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                finish();
            }
        });
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntaje=0;
                fallas=0;
                valorSeleccionado=-1;
                // int indice=1;
                Bitmap bpm= BitmapFactory.decodeResource(getResources(), R.drawable.carta0);
                for (ImageButton img:imgBotones)
                {
                    //bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta0
                    img.setImageBitmap(bpm);
                    img.setEnabled(true);
                }
                lblPuntaje.setText(puntaje+"");
                lblFallas.setText(fallas+"");
                desordenarCartas();

            }
        });

        agregarEventosCartas();

        lblPuntaje= (TextView) findViewById(R.id.txtPuntaje);
        lblFallas= (TextView) findViewById(R.id.txtFallas);
        //lblPuntaje.setText(0);

    }

    private Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta0);
            imgBotones[valorSeleccionado].setImageBitmap(bpm);
            valorSeleccionado = -1;
            imgBotones[valorBorrar].setImageBitmap(bpm);
        }
    };

    private void desordenarCartas()
    {
        Random rnd=new Random();
        int aux;
        int indiceAux;

        for(int i=0;i<valores.length;i++)
        {
            aux=valores[i]; //respaldo el valor del indice
            indiceAux=rnd.nextInt(6); //nuevo indice para cambiar el valor

            valores[i]=valores[indiceAux];
            valores[indiceAux]=aux;

        }

    }

    private void controlador(int opcion,ImageButton img)
    {
        Bitmap bpm=null;
        opcion--;
        switch (valores[opcion])
        {
            case 1: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta1);break;
            case 2: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta2);break;
            case 3: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta3);break;
            case 4: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta4);break;
            case 5: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta5);break;
            case 6: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta6);break;
        }

        if(valorSeleccionado==-1) //para verificar que es la primera carta seleccionada
        {
            valorSeleccionado=opcion;
            img.setImageBitmap(bpm); //dibujas la carta
            img.setEnabled(false);
            resp=img;
        }
        else
        {
            if(valores[valorSeleccionado]==valores[opcion]) //las dos son iguales
            {
                puntaje++;
                lblPuntaje.setText(puntaje+"x10");

               // Toast.makeText(this, "!Bien!", Toast.LENGTH_LONG).show(); //solo es un mensaje
                if (puntaje==6){
                    startActivity(new Intent(Memoria.this, finjuego.class));
                    finish();
                }
                img.setEnabled(false);
                resp.setEnabled(false);
                img.setImageBitmap(bpm);
                valorSeleccionado=-1; //para indicar que otra vez no hya carta girada
                // Toast.makeText(this, "mensaje", Toast.LENGTH_LONG).show();
            }
            else //son diferente
            {
                resp.setEnabled(true);
                fallas++;
                lblFallas.setText(fallas + "x5");
                valorBorrar=opcion; //el valor que tengo que girar
                img.setImageBitmap(bpm);
                //runOnUiThread(new Runnable()
                Thread hilo=new Thread(this);
                hilo.start(); //
                // Toast.makeText(this,"!Mal!",Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,"!Mal!",100).show();


            }
        }


    }

    private void iniciarCartas()
    {
        //carga todas las vistas a la clase para anejar
        puntaje=0;
        fallas=0;
        img1= (ImageButton) findViewById(R.id.carta1);
        img1.setEnabled(true);
        img2= (ImageButton) findViewById(R.id.carta2);
        img2.setEnabled(true);
        img3= (ImageButton) findViewById(R.id.carta3);
        img3.setEnabled(true);
        img4= (ImageButton) findViewById(R.id.carta4);
        img4.setEnabled(true);
        img5= (ImageButton) findViewById(R.id.carta5);
        img5.setEnabled(true);
        img6= (ImageButton) findViewById(R.id.carta6);
        img6.setEnabled(true);
        img7= (ImageButton) findViewById(R.id.carta7);
        img7.setEnabled(true);
        img8= (ImageButton) findViewById(R.id.carta8);
        img8.setEnabled(true);
        img9= (ImageButton) findViewById(R.id.carta9);
        img9.setEnabled(true);
        img10= (ImageButton) findViewById(R.id.carta10);
        img10.setEnabled(true);
        img11= (ImageButton) findViewById(R.id.carta11);
        img11.setEnabled(true);
        img12= (ImageButton) findViewById(R.id.carta12);
        img12.setEnabled(true);

    }

    private void agregarEventosCartas() {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.nombreImagen);
                //Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.carta1);
                controlador(1,img1);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(2, img2);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(3, img3);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(4, img4);
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(5, img5);
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(6, img6);
            }
        });


        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(7, img7);
            }
        });


        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(8, img8);
            }
        });


        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(9, img9);
            }
        });


        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(10, img10);
            }
        });

        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(11, img11);
            }
        });

        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(12, img12);
            }
        });




    }




    @Override
    public void run() {
        SystemClock.sleep(500);
        // Bitmap bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta0);
        // imgBotones[valorSeleccionado].setImageBitmap(bpm);
        //  valorSeleccionado=-1;
        //  imgBotones[valorBorrar].setImageBitmap(bpm);
        Message msg = new Message();
        msg.obj = 12;
        puente.sendMessage(msg);

    }
    @Override
         public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Logica.ju=5;
            Logica.punt=0;
            Memoria.puntaje=0;
            Memoria.fallas=0;
            Evaluate.n=0;
            finish();

            return true;

        }

        return super.onKeyDown(keyCode, event);
    }
}


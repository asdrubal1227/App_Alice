package com.example.brayanasdrubal.appalice;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private CameraUpdate mCam;

    public TextView data;
    public int n=0;

    public static double lat1=0,lon1=0,lat2=0,lon2=0,lat3=0,lon3=0;
    public static String ubica1="",ubica2="",ubica3="";
    LocationManager locationManager = null;

    @TargetApi(Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        /*messageTextView = (TextView) findViewById(R.id.message_id);
        messageTextView2 = (TextView) findViewById(R.id.message_id2);*/

		/* Use the LocationManager class to obtain GPS locations */
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener mlocListener = new MyLocationListener();
        mlocListener.setMainActivity(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) mlocListener);

        /*messageTextView.setText("LocationListener agregado");
        messageTextView2.setText("");*/
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mCam= CameraUpdateFactory.newLatLngZoom(new LatLng(6.2657628, -75.5748746), 12);
        mMap.animateCamera(mCam);
        // Add a marker in Sydney and move the camera
        /*LatLng udea1 = new LatLng(lat1, lon1);
        mMap.addMarker(new MarkerOptions().position(udea1).title(ubica1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))).showInfoWindow();
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0, 0)));
        // MarkerOptions marker = new MarkerOptions().position(udea).title("Hello Maps");
        //setMarker(udea, "UdeA","Alma Mater");
        //googleMap.addMarker(marker.showInfoWindow();
       // mCam=CameraUpdateFactory.newLatLngZoom(udea1,12);
        //mMap.animateCamera(mCam);

        LatLng udea2 = new LatLng(lat2, lon2);
                mMap.addMarker(new MarkerOptions().position(udea2).title(ubica2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))).showInfoWindow();
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0, 0)));
                // MarkerOptions marker = new MarkerOptions().position(udea).title("Hello Maps");
                //setMarker(udea, "UdeA","Alma Mater");
                //googleMap.addMarker(marker.showInfoWindow();
        //       mCam=CameraUpdateFactory.newLatLngZoom(udea2,12);
        // mMap.animateCamera(mCam);

                if (MainActivity.num !=1){
                    LatLng udea3 = new LatLng(lat3, lon3);
                    mMap.addMarker(new MarkerOptions().position(udea3).title(ubica3).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))).showInfoWindow();
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0, 0)));
                    // MarkerOptions marker = new MarkerOptions().position(udea).title("Hello Maps");
                    //setMarker(udea, "UdeA","Alma Mater");
                    //googleMap.addMarker(marker.showInfoWindow();
                    //         mCam=CameraUpdateFactory.newLatLngZoom(udea3, 12);
                    //         mMap.animateCamera(mCam);
                }

        TextView mt = (TextView) findViewById(R.id.maptext);
        mt.setText(LisTabs.mapt);*/

    }

    private void setMarker(LatLng position, String title, String info){//, int icon){
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(info)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))//fromresorces(icon)
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setLocation(Location loc) {
        //Obtener la dirección de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                   /* messageTextView2.setText("Mi dirección es: \n"
                            + address.getAddressLine(0));*/
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





    /* Class My Location Listener */
    public class MyLocationListener implements LocationListener {
        MapsActivity mainActivity;

        public MyLocationListener getMapsActivity() {
            return this;
        }

        public void setMainActivity(MapsActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este método se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la detección de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Mi ubicación actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
           /* messageTextView.setText(Text);*/

            /*lat=Double.parseDouble(latitud.getText().toString());
            lon=Double.parseDouble(longitud.getText().toString());*/
            if (n==5) {
                LatLng pos = new LatLng(loc.getLatitude(), loc.getLongitude());
                //setMarker(pos, "UdeA", "Alma Mater");
                //MarkerOptions marker = new MarkerOptions().position(pos).title("Hello Maps");
                mCam = CameraUpdateFactory.newLatLngZoom(pos, 12);
                mMap.animateCamera(mCam);
                //mMap.addMarker(marker).showInfoWindow();
                mMap.addMarker(new MarkerOptions().position(pos).title("Aqui!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))).showInfoWindow();

                setLocation(loc);
                n=10;
            }else if (n<7){
                n=n+1;
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este m�todo se ejecuta cuando el GPS es desactivado
            /*messageTextView.setText("GPS Desactivado");*/
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este m�todo se ejecuta cuando el GPS es activado
           /* messageTextView.setText("GPS Activado");*/
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este m�todo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizaci�n (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temp�ralmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }

    }/* End of Class MyLocationListener */

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

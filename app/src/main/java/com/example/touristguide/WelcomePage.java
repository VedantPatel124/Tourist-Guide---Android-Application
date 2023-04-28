package com.example.touristguide;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class WelcomePage extends AppCompatActivity implements LocationListener {

    TextView tvid,tvname,tvcon;
    TextView tvlat,tvlong;
    TextView tvcountry,tvcity;
    LocationManager locationManager;
    WebView web;
    Spinner sp;
    String cityarray[]={"Vadodara","Surat","Ahemedabad"};
    String adata;

    private double longm,latm;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;



    //EditText edtdest;
    Button btngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        tvid = (TextView)findViewById(R.id.tvid);
        tvname = (TextView)findViewById(R.id.tvname);
        tvcon = (TextView)findViewById(R.id.tvcon);

        Intent i = getIntent();
        tvid.setText("Id = "+i.getStringExtra("uid"));
        tvname.setText("Name = "+i.getStringExtra("uname"));
        tvcon.setText("Contact No = "+i.getStringExtra("ucon"));

        
        tvlat = (TextView) findViewById(R.id.tvlat);
        tvlong = (TextView) findViewById(R.id.tvlong);
        tvcountry = (TextView) findViewById(R.id.tvcontry);
        tvcity = (TextView) findViewById(R.id.tvcity);

        web = (WebView)findViewById(R.id.web);
        
       // edtdest = (EditText)findViewById(R.id.edtdest);
        btngo = (Button)findViewById(R.id.btngo);
        sp = (Spinner)findViewById(R.id.sp);
        ArrayAdapter adapter = new ArrayAdapter(WelcomePage.this,android.R.layout.simple_list_item_1,cityarray);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adata = cityarray[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        checkLocationPermission();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


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

        //  web.loadUrl("https://www.google.com/maps/@22.3066654,73.1467615,15z");
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);

        loc_fun(location);

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String mm = adata;
                    mm = "city = " + mm;
                    mm = mm.toLowerCase();
                    if (mm.equals(tvcity.getText().toString().toLowerCase())) {
                        Intent i = new Intent(WelcomePage.this, cityshorting.class);
                        i.putExtra("cityid2",adata);
                        i.putExtra("mlat",latm);
                        i.putExtra("mlong",longm);
                        //Toast.makeText(WelcomePage.this, "Lat = "+latm+"\nLong = "+longm, Toast.LENGTH_SHORT).show();

                        startActivity(i);

//                        Toast.makeText(WelcomePage.this, "Future Work", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(WelcomePage.this, retrivecity.class);
                        i.putExtra("cityid2",adata);
                        startActivity(i);
                        //Toast.makeText(WelcomePage.this, "Miss Match...", Toast.LENGTH_SHORT).show();
                    }


            }
        });

        /*
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtdest.getText().toString().isEmpty()) {
                    String mm = edtdest.getText().toString();
                    mm = "city = " + mm;
                    mm = mm.toLowerCase();
                    if (mm.equals(tvcity.getText().toString().toLowerCase())) {
                        Intent i = new Intent(WelcomePage.this, cityshorting.class);
                        i.putExtra("cityid2",edtdest.getText().toString());
                        startActivity(i);

//                        Toast.makeText(WelcomePage.this, "Future Work", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(WelcomePage.this, retrivecity.class);
                        i.putExtra("cityid2",edtdest.getText().toString());
                        startActivity(i);
                        //Toast.makeText(WelcomePage.this, "Miss Match...", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    {
                        Toast.makeText(WelcomePage.this, "Enter Destinition", Toast.LENGTH_SHORT).show();
                    }
            }
        });
*/
    }


    @Override
    public void onLocationChanged(Location location) {
        longm = location.getLongitude();
        latm = location.getLatitude();

        tvlat.setText(String.valueOf("Lat = "+latm));
        tvlong.setText(String.valueOf("Long = "+longm));

        //web.loadUrl("http://maps.google.com/maps?q="+latm+","+longm);


        web.loadUrl("https://www.google.com/maps?q=@"+latm+","+longm);

        web.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private  void loc_fun(Location loc)
    {
        try {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses = null;


            addresses = geocoder.getFromLocation(latm,longm,1);
            String country = addresses.get(0).getCountryName();
            String city = addresses.get(0).getLocality();

            tvcountry.setText("County = "+country);
            tvcity.setText("City = "+city);
            //Toast.makeText(this, ""+country+city, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Is Needed !")
                        .setMessage("Please Allow To be Continue..")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(WelcomePage.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {

                String provider = locationManager.getBestProvider(new Criteria(), false);
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenufile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(WelcomePage.this,LoginPage.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}

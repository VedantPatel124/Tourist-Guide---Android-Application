package com.example.touristguide;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class navlocation extends AppCompatActivity {

    WebView web;
    Double latm,longm;
    String sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navlocation);

        web = (WebView)findViewById(R.id.web);

        Intent i = getIntent();

        latm = i.getDoubleExtra("mlat", 0);
        longm = i.getDoubleExtra("mlong", 0);
        sid = i.getStringExtra("id");
        web.getSettings().setJavaScriptEnabled(true);

        if(sid.equals("6"))
        {
            web.loadUrl("https://www.google.co.in/maps/dir/"+latm+","+longm+"/BAPS+Shri+Swaminarayan+Mandir,+Shastri+Yagnapurushdas+Marg,+Atladara,+Vadodara,+Gujarat+390012/@"+latm+","+longm+",14z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x395fc63f95df6cdd:0x657cf8092a78cbe4!2m2!1d"+longm+"!2d"+latm+"");
        }
        if(sid.equals("5"))
        {
            web.loadUrl("https://www.google.co.in/maps/dir/"+latm+","+longm+"//Sayaji+Baug+Zoo,+Vinoba+Bhave+Rd,+Near+Kala+Ghoda,+Dak+Bunglaw,+Sayajiganj,+Vadodara,+Gujarat/@"+latm+","+longm+",14z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x395fcf4e4d002719:0x2204aafedf541231!2m2!1d"+longm+"!2d"+latm+"");
        }
        if(sid.equals("4"))
        {
            web.loadUrl("https://www.google.co.in/maps/dir/"+latm+","+longm+"/Kirti+Mandir,+Kothi+Road,+Dak+Bunglaw,+Sayajiganj,+Vadodara,+Gujarat/@"+latm+","+longm+",14z/data=!3m1!4b1!4m10!4m9!1m1!4e1!1m5!1m1!1s0x395fcf511d7a337d:0xcb6b9225f314aa3d!2m2!1d"+longm+"!2d"+latm+"");
        }
        if(sid.equals("3"))
        {
            web.loadUrl("https://www.google.co.in/maps/dir/"+latm+","+longm+"/Ajwa+Fun+World,+Ajwa+Water+Park,+Ajwa+Resort,+Vadodara,+Gujarat/@"+latm+","+longm+",12z/data=!3m1!4b1!4m10!4m9!1m1!4e1!1m5!1m1!1s0x395fd19c507342fb:0xa03d28a1b1dc0290!2m2!1d"+longm+"!2d"+latm+"!3e0");
        }
        if(sid.equals("2"))
        {
            web.loadUrl("https://www.google.co.in/maps/dir/"+latm+","+longm+"/Sindhrot+Check+Dam,+Gujarat/@"+latm+","+longm+",13z/data=!3m1!4b1!4m10!4m9!1m1!4e1!1m5!1m1!1s0x395fb7b4be673f59:0x94cbbba9730829e8!2m2!1d"+longm+"!2d"+latm+"!3e0");
        }
        if(sid.equals("1"))
        {
            web.loadUrl("https://www.google.co.in/maps/dir/"+latm+","+longm+"/Lakshmi+Vilas+Palace,+J+N+Marg,+Moti+Baug,+Vadodara,+Gujarat/@"+latm+","+longm+",14z/data=!3m1!4b1!4m10!4m9!1m1!4e1!1m5!1m1!1s0x395fc5f0af2653f7:0x21b69c177c300b0b!2m2!1d"+longm+"!2d"+latm+"!3e0");
        }
        //Toast.makeText(this, "Lat = "+latm+"\nLong = "+longm, Toast.LENGTH_SHORT).show();

       // web.loadUrl("https://www.google.co.in/maps/dir/"+latm+","+longm+"/BAPS+Shri+Swaminarayan+Mandir,+Shastri+Yagnapurushdas+Marg,+Atladara,+Vadodara,+Gujarat+390012/@"+latm+","+longm+",14z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x395fc63f95df6cdd:0x657cf8092a78cbe4!2m2!1d"+longm+"!2d"+latm+"");
        web.getSettings().setJavaScriptEnabled(true);

    }
}

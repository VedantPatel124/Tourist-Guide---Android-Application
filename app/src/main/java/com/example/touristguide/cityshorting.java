package com.example.touristguide;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class cityshorting extends AppCompatActivity {

    ListView lst;
    ArrayList<pojo> list;
    String url,result,utime1,mmid;
    double latm;
    double longm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        lst=(ListView)findViewById(R.id.lst);

        Button btnrefresh = (Button)findViewById(R.id.btnrefresh);

        Intent i = getIntent();
        String mm = i.getStringExtra("cityid2");
        latm = i.getDoubleExtra("mlat", 0);
        longm = i.getDoubleExtra("mlong", 0);
//
//        latm = Double.parseDouble(m);
//        longm = Double.parseDouble(n);

      //  Toast.makeText(this, "Lat = "+latm+"\nLong = "+longm, Toast.LENGTH_SHORT).show();


        final Dialog dialog = new Dialog(cityshorting.this);
                dialog.setContentView(R.layout.custdialogtime);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button


                final EditText edt = (EditText)dialog.findViewById(R.id.edtname);



                Button dialogButton = (Button) dialog.findViewById(R.id.btnok);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(cityshorting.this, ""+edt.getText().toString(), Toast.LENGTH_SHORT).show();
                          utime1 = edt.getText().toString();
                        dialog.dismiss();

                        url = "https://anabatic-waist.000webhostapp.com/meettry.php";

                        new retrive1().execute();
                    }
                });

                dialog.show();





//        url = "https://anabatic-waist.000webhostapp.com/meettry.php";
//
//        new retrive1().execute();

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://anabatic-waist.000webhostapp.com/meettry.php";

                new retrive2().execute();
            }
        });


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pojo p = (pojo)list.get(i);

                final String sid = p.getCityid();
                mmid = p.getCityid();
                final String srat = p.getUserrat();
                final String ssug = p.getUsersug();


                final Dialog dialog = new Dialog(cityshorting.this);
                dialog.setContentView(R.layout.custdialograt);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button


                final RatingBar rat = (RatingBar)dialog.findViewById(R.id.rat);
                final EditText edt = (EditText)dialog.findViewById(R.id.edtsug);



                Button dialogButton = (Button) dialog.findViewById(R.id.btnok);
                Button btnnav = (Button) dialog.findViewById(R.id.btnnav);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(LoginPage.this, ""+edt.getText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        //Toast.makeText(cityshorting.this, " "+srat+" "+rat.getRating(), Toast.LENGTH_SHORT).show();

                        String rr = " "+srat+" "+rat.getRating();

                        String sss = " "+ssug+" "+edt.getText().toString();

                        url = "https://anabatic-waist.000webhostapp.com/updaterat.php?citylocid="+sid+"&userrat="+rr+"&usersug="+sss;
                        //url="https://anabatic-waist.000webhostapp.com/updaterat.php?citylocid="+sid+"&userrat="+rr;
                        new insert().execute();
                    }
                });
                btnnav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(cityshorting.this,navlocation.class);
                        i.putExtra("mlat",latm);
                        i.putExtra("mlong",longm);
                        i.putExtra("id",mmid);
                       // Toast.makeText(cityshorting.this, ""+mmid, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(cityshorting.this, ""+sid, Toast.LENGTH_SHORT).show();
                       // Toast.makeText(cityshorting.this, "Lat = "+latm+"\nLong = "+longm, Toast.LENGTH_SHORT).show();

                        startActivity(i);
                        dialog.dismiss();
                    }
                });

                dialog.show();

                //Toast.makeText(cityshorting.this, ""+sid, Toast.LENGTH_SHORT).show();
            }
        });

    }

    class retrive1 extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(cityshorting.this);
            pd.setTitle("Retriving...");
            pd.setMessage("Please wait....");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            json o = new json();
            result=o.insert(url);
            list=new ArrayList<>();
            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");

                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1 = ja.getJSONObject(i);
                    pojo p = new pojo();
                    p.setCityid(jo1.getString("citylocid"));
                    p.setCityname(jo1.getString("citylocname"));
                    p.setCitydesc(jo1.getString("distancefromcity"));
                    p.setLoc1(jo1.getString("totalarealocation"));
                    p.setLoc1desc(jo1.getString("citylocplaces"));
                    p.setLoc1time(jo1.getString("cityloctime"));
                    p.setRating(jo1.getString("cityrating"));
                    p.setUserrat(jo1.getString("userrat"));
                    p.setUsersug(jo1.getString("usersug"));


                    list.add(p);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adaptervadodara adp = new adaptervadodara(cityshorting.this,list);
            adp.utime=String.valueOf(utime1);
            lst.setAdapter(adp);

            if(pd.isShowing())
            {
                pd.dismiss();
            }
        }
    }

    class retrive2 extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(cityshorting.this);
            pd.setTitle("Retriving...");
            pd.setMessage("Please wait....");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            json o = new json();
            result=o.insert(url);
            list=new ArrayList<>();
            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");

                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1 = ja.getJSONObject(i);
                    pojo p = new pojo();
                    p.setCityid(jo1.getString("citylocid"));
                    p.setCityname(jo1.getString("citylocname"));
                    p.setCitydesc(jo1.getString("distancefromcity"));
                    p.setLoc1(jo1.getString("totalarealocation"));
                    p.setLoc1desc(jo1.getString("citylocplaces"));
                    p.setLoc1time(jo1.getString("cityloctime"));
                    p.setRating(jo1.getString("cityrating"));
                    p.setUserrat(jo1.getString("userrat"));
                    p.setUsersug(jo1.getString("usersug"));


                    list.add(p);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adaptervadodara adp = new adaptervadodara(cityshorting.this,list);

            lst.setAdapter(adp);

            if(pd.isShowing())
            {
                pd.dismiss();
            }
        }
    }




    class insert extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(cityshorting.this);
            pd.setTitle("Giving Ratings...");
            pd.setMessage("Please wait....");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            json j = new json();
            result=j.insert(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pd.isShowing())
            {
                pd.dismiss();
            }
            Toast.makeText(cityshorting.this,result, Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addlayout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().toString().equals("Advertisement"))
        {
            Intent i = new Intent(cityshorting.this,Advertise.class);
            startActivity(i);
        }
        else
            {
            Intent i = new Intent(cityshorting.this,LoginPage.class);
            startActivity(i);
            }
            return super.onOptionsItemSelected(item);
    }
}

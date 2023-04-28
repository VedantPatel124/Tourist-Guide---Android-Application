package com.example.touristguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class retrivecityloc extends AppCompatActivity
{

    ListView lst;
    ArrayList<pojo> list;
    String url,result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        lst=(ListView)findViewById(R.id.lst);
        Button btnrefresh = (Button)findViewById(R.id.btnrefresh);
        btnrefresh.setVisibility(View.GONE);
        //url = "https://studentdemo36.000webhostapp.com/retrive.php";


        Intent i = getIntent();
        String sid = i.getStringExtra("id");

        url = "https://anabatic-waist.000webhostapp.com/selectcityloc.php?cityid2="+sid;
        //url = "https://anabatic-waist.000webhostapp.com/selectcity.php?cityid="+sid;

        new retrive1().execute();


    }


    class retrive1 extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(retrivecityloc.this);
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
            adaptercitydesc adp = new adaptercitydesc(retrivecityloc.this,list);
            lst.setAdapter(adp);
            if(pd.isShowing())
            {
                pd.dismiss();
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
        Intent i = new Intent(retrivecityloc.this,LoginPage.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}

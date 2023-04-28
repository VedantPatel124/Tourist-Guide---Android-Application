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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class retrivecity extends AppCompatActivity
{

    ListView lst;
    ArrayList<pojo> list;
    String url,result,mm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        lst=(ListView)findViewById(R.id.lst);
        Button btnrefresh = (Button)findViewById(R.id.btnrefresh);
        btnrefresh.setVisibility(View.GONE);

        Intent i = getIntent();
        mm = i.getStringExtra("cityid2");
        //Toast.makeText(this, ""+mm, Toast.LENGTH_SHORT).show();

        //url = "https://studentdemo36.000webhostapp.com/retrive.php";

        url = "https://anabatic-waist.000webhostapp.com/retrivecity.php";

        new retrive1().execute();


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pojo p = (pojo)list.get(position);

                final String sid = p.getCityname();


                        Intent i = new Intent(retrivecity.this,retrivecityloc.class);
                        i.putExtra("id",sid);
                //Toast.makeText(retrivecity.this, ""+sid, Toast.LENGTH_SHORT).show();
                        startActivity(i);

            }
        });


    }


    class retrive1 extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(retrivecity.this);
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
                    p.setCityid(jo1.getString("cityid"));
                    p.setCityname(jo1.getString("cityname"));
                    p.setCitydesc(jo1.getString("citydesc"));

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
            adapter adp = new adapter(retrivecity.this,list);
            adp.ucity = mm;
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
        Intent i = new Intent(retrivecity.this,LoginPage.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}

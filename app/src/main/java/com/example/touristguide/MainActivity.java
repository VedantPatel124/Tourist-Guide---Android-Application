package com.example.touristguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText edtname,edtnum,edtid,edtpass,edtrepass;
    Button btnsignup;
    TextView tvlogin;

    String sname , spassword , url , res,result,ssname,sspass,score,meeturl,scon,susername;
    int ssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtid = (EditText)findViewById(R.id.edtid);
        edtpass = (EditText)findViewById(R.id.edtpass);
        edtrepass = (EditText)findViewById(R.id.edtrepass);
        edtnum = (EditText)findViewById(R.id.edtnum);
        edtname = (EditText)findViewById(R.id.edtname);

        tvlogin = (TextView)findViewById(R.id.tvlogin);

        btnsignup = (Button)findViewById(R.id.btnsignup);

        edtid.addTextChangedListener(textWatcher);
        edtnum.addTextChangedListener(textWatcher);



        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginPage.class);
                startActivity(i);
            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sname=edtid.getText().toString();
                spassword=edtpass.getText().toString();
                scon = edtnum.getText().toString();
                susername = edtname.getText().toString();
                
                if(spassword.length()>=8) {

                    // meeturl = "https://clerkliest-excuse.000webhostapp.com/userrepeat.php?uid="+sname;

                    meeturl = "https://anabatic-waist.000webhostapp.com/userrepeat.php?email=" + sname;
                    new m().execute();
                }
                else
                    {
                        Toast.makeText(MainActivity.this, "Password is week", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(edtid.getText().toString().contains("@gmail.com")&&edtnum.getText().toString().length()==10)
            {
               btnsignup.setEnabled(true);
            }

            else
                {
                    btnsignup.setEnabled(false);
                }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    class m extends AsyncTask<Void,Void,Void> {

        ProgressDialog pdc;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdc=new ProgressDialog(MainActivity.this);
            pdc.setTitle("Checking Username...");
            pdc.setMessage("Please Wait..");
            pdc.setCancelable(true);
            pdc.setIndeterminate(false);
            pdc.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            json j = new json();
            res = j.insert(meeturl);
            //list=new ArrayList<>();
            try {
                JSONObject jo = new JSONObject(res);
                JSONArray ja = jo.getJSONArray("data");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo1 = ja.getJSONObject(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (res.length()>3)
            {
                if(pdc.isShowing())
                {
                    pdc.dismiss();
                }
                Toast.makeText(MainActivity.this, "User name already Exist...", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
            else
            {
                if(pdc.isShowing())
                {
                    pdc.dismiss();


                    url="https://anabatic-waist.000webhostapp.com/insert.php?name="+susername+"&email="+sname+"&password="+spassword+"&contact="+scon;
                    new insert().execute();
                }
            }
        }
    }

    class insert extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(MainActivity.this);
            pd.setTitle("Creating Account...");
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
            Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
            edtid.setText("");
            edtpass.setText("");
        }
    }
}

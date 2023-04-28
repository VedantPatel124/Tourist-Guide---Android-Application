package com.example.touristguide;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {

    EditText edtemailid,edtpass;
    Button btnlogin;
    String sname,spassword,url,result;
    String sscontact,ssemail,sspss,ssusername;
    TextView tvlogin,tvforpass;

    int ssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        edtemailid = (EditText)findViewById(R.id.edtemailid);
        edtpass = (EditText)findViewById(R.id.edtpass);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        tvlogin = (TextView)findViewById(R.id.tvlogin);
        tvforpass = (TextView)findViewById(R.id.tvforpass);

        
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sname=edtemailid.getText().toString();
                spassword=edtpass.getText().toString();

                url="https://anabatic-waist.000webhostapp.com/uservalidate.php?email="+sname+"&password="+spassword;
                new validate().execute();

            }
        });


        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginPage.this,MainActivity.class);
                startActivity(i);
            }
        });

        tvforpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(LoginPage.this);
                dialog.setContentView(R.layout.customdialog);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button


                final EditText edt = (EditText)dialog.findViewById(R.id.edtname);



                Button dialogButton = (Button) dialog.findViewById(R.id.btnok);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(LoginPage.this, ""+edt.getText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();


                        url="https://anabatic-waist.000webhostapp.com/validatephone.php?contact="+edt.getText().toString();
                        new validatemobile().execute();
                    }
                });

                dialog.show();
            }
        });
    }

    class validate extends AsyncTask<Void,Void,Void> {

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(LoginPage.this);
            pd.setTitle("Authenticating...");
            pd.setMessage("Please Wait..");
            pd.setCancelable(true);
            pd.setIndeterminate(false);
            pd.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            json j = new json();
            result = j.insert(url);
            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo1 = ja.getJSONObject(i);
                    sscontact=jo1.getString("contact");
                    ssusername=jo1.getString("name");
                    ssemail=jo1.getString("email");
                    sspss=jo1.getString("password");
                    ssid=jo1.getInt("id");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (result.length() < 3) {
                Toast.makeText(LoginPage.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            } else
            {
                if(pd.isShowing())
                {
                    pd.dismiss();
                    Intent i = new Intent(LoginPage.this,WelcomePage.class);
                    i.putExtra("uid",String.valueOf(ssid));
                    i.putExtra("uname",ssusername);
                    i.putExtra("ucon",sscontact);
                    startActivity(i);
                }
            }
        }
    }



    class validatemobile extends AsyncTask<Void,Void,Void> {

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(LoginPage.this);
            pd.setTitle("Getting Data...");
            pd.setMessage("Please Wait..");
            pd.setCancelable(true);
            pd.setIndeterminate(false);
            pd.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            json j = new json();
            result = j.insert(url);
            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo1 = ja.getJSONObject(i);
                        sscontact=jo1.getString("contact");
                        ssusername=jo1.getString("name");
                        ssemail=jo1.getString("email");
                        sspss=jo1.getString("password");
                        ssid=jo1.getInt("id");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (result.length() < 3) {
                Toast.makeText(LoginPage.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            } else
            {
                if(pd.isShowing())
                {
                    pd.dismiss();


                    final Dialog dialog = new Dialog(LoginPage.this);
                    dialog.setContentView(R.layout.customdialog);
                    dialog.setTitle("Title...");

                    // set the custom dialog components - text, image and button






                    Button dialogButton = (Button) dialog.findViewById(R.id.btnok);
                    TextView tv = (TextView)dialog.findViewById(R.id.text);
                    EditText edt = (EditText)dialog.findViewById(R.id.edtname);
                    tv.setText("Id = "+ssemail+"\nPassword = "+sspss);
                    edt.setVisibility(View.GONE);

                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(LoginPage.this, ""+edt.getText().toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    });

                    dialog.show();
                    //Toast.makeText(LoginPage.this, ""+ssemail+" "+sspss, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

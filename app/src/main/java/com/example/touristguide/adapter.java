package com.example.touristguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class adapter extends BaseAdapter {
    Context context;
    ArrayList<pojo> list;

    String urlpic,ucity;

    public adapter(Context context, ArrayList<pojo> list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.look,null);

        TextView id = (TextView)v.findViewById(R.id.txtid);
        TextView name = (TextView)v.findViewById(R.id.txtname);
        TextView desc = (TextView)v.findViewById(R.id.txtcitydesc);
        ImageView img = (ImageView)v.findViewById(R.id.img);



        pojo p = (pojo)list.get(position);

        id.setText(p.getCityid());
        name.setText(p.getCityname());
        desc.setText(p.getCitydesc());

        if(!name.getText().toString().equals(ucity))
        {
            id.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            desc.setVisibility(View.GONE);

            img.setVisibility(View.GONE);

            // Toast.makeText(context, "utime="+utime, Toast.LENGTH_SHORT).show();
            // Toast.makeText(context, ""+p.getCitydesc(), Toast.LENGTH_SHORT).show();
        }


            urlpic = "https://anabatic-waist.000webhostapp.com/citypics/" + p.getCityid() + ".jpg";

            Picasso.with(v.getContext()).load(urlpic).into(img);

        return v;
    }
}

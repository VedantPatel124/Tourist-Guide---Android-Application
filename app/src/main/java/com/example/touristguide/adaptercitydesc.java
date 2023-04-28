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

public class adaptercitydesc extends BaseAdapter {
    Context context;
    ArrayList<pojo> list;

    String urlpic;

    public adaptercitydesc(Context context, ArrayList<pojo> list)
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
        View v = inflater.inflate(R.layout.lookcitydesc,null);

        TextView id = (TextView)v.findViewById(R.id.txtid);
        TextView name = (TextView)v.findViewById(R.id.txtname);
        TextView desc = (TextView)v.findViewById(R.id.txtcitydesc);
        TextView loc = (TextView)v.findViewById(R.id.txtloc);
        TextView locplaces = (TextView)v.findViewById(R.id.txtlocplaces);
        TextView loctime = (TextView)v.findViewById(R.id.txtloctime);
        ImageView img = (ImageView)v.findViewById(R.id.img);




        pojo p = (pojo)list.get(position);

        id.setText("City Id = "+p.getCityid());
        name.setText("Place Name ="+p.getCityname());
        desc.setText("Distance from City = "+p.getCitydesc()+" Km");
        loc.setText("Total Area Location = "+p.getLoc1()+" Km");
        locplaces.setText("Places = "+p.getLoc1desc());
        loctime.setText("Total Time = "+p.getLoc1time());

        //score.setText(p.get());


//        if(Integer.parseInt(id.getText().toString())>60)
//        {
//            //urlpic = "https://3636apps.000webhostapp.com/imageupload/uploads/" + p.getId() + ".jpg";
//            //Toast.makeText(context, ""+id.getText().toString(), Toast.LENGTH_SHORT).show();
//        //    Picasso.with(v.getContext()).load(urlpic).into(img);
//        }
//        else {
            urlpic = "https://anabatic-waist.000webhostapp.com/citypics/" + p.getCityid() + ".jpg";

            Picasso.with(v.getContext()).load(urlpic).into(img);
            //img.setImageResource(Integer.parseInt(urlpic));
      //  }
        return v;
    }
}

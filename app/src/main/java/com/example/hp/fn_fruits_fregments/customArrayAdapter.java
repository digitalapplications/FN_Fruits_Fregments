package com.example.hp.fn_fruits_fregments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 10/3/2016.
 */
public class customArrayAdapter extends ArrayAdapter<Fruit> {

    Context context;
    List<Fruit> objects;
    Fruit fruit;
    ImageView iv;
    public customArrayAdapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        fruit = objects.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mylayout,null);


        TextView tv_name = (TextView) view.findViewById(R.id.tv_customName);
        tv_name.setText(fruit.getFname());

        tv_name= (TextView) view.findViewById(R.id.tv_calories);
        tv_name.setText("Calories :"+fruit.getFcal());

        tv_name = (TextView) view.findViewById(R.id.tv_values);
        tv_name.setText("Value : "+fruit.getFval());


        Log.d("decode", fruit.getFpic());



        iv = (ImageView) view.findViewById(R.id.iv_custom);
        final byte[] decodedString = Base64.decode(fruit.getFpic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        iv.setImageBitmap(decodedByte);


        return view;
    }

}

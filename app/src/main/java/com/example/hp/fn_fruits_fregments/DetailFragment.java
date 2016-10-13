package com.example.hp.fn_fruits_fregments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HP on 10/13/2016.
 */

public class DetailFragment extends Fragment {

    Fruit fruit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if(b!=null){
            fruit = new Fruit(b);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.detailfragment,container,false);
        if(fruit !=null) {

            ImageView iv = (ImageView) rootview.findViewById(R.id.detail_pic);
            String fpic = fruit.getFpic();

            final byte[] decodedString = Base64.decode(fpic, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            iv.setImageBitmap(decodedByte);

            TextView tv_name = (TextView) rootview.findViewById(R.id.fruitname);
            tv_name.setText(fruit.getFname());

            TextView tv_intro = (TextView) rootview.findViewById(R.id.tv_intro);
            tv_intro.setText(fruit.getFintro());

            TextView tv_detail = (TextView) rootview.findViewById(R.id.tv_detail);
            tv_detail.setText(fruit.getFdetail());
        }
        return rootview;
    }
}

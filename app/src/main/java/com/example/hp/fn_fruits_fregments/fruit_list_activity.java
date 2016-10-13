package com.example.hp.fn_fruits_fregments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class fruit_list_activity extends AppCompatActivity implements FruitListFragment.callBack{

    public static String title;
    public boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list);

        if(findViewById(R.id.detailContainer)!=null){
            isTwoPane = true;
        }
        title = getIntent().getStringExtra("title");
        Log.d("title",title);
        //getFragmentManager().beginTransaction().replace(R.id.activity_fruit_list,new FruitListFragment()).commit();
    }

    @Override
    public void onItemSelected(Fruit fruit) {
        Bundle b = fruit.toBundle();

        if(isTwoPane){
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(b);

            getFragmentManager().beginTransaction().replace(R.id.detailContainer,fragment).commit();
        }
        else{
            Intent intent = new Intent(this,fruitDetailActivity.class);
            intent.putExtra("bundle",b);
            startActivityForResult(intent,1001);
        }
    }
}

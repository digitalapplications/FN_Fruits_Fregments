package com.example.hp.fn_fruits_fregments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class fruitDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);

        DetailFragment fragment = new DetailFragment();
        Bundle b = getIntent().getBundleExtra("bundle");
        fragment.setArguments(b);
        getFragmentManager().beginTransaction().add(R.id.myDetailContainer,fragment).commit();
    }
}

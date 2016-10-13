package com.example.hp.fn_fruits_fregments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btn_hot,btn_cold;
    FruitDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new FruitDbHelper(this);
        btn_hot = (Button) findViewById(R.id.btn_hot);
        btn_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,fruit_list_activity.class);
                intent.putExtra("title","cold fruit");
                startActivity(intent);
            }
        });

        btn_cold = (Button) findViewById(R.id.btn_cold);
        btn_cold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,fruit_list_activity.class);
                intent.putExtra("title","hot fruit");
                startActivity(intent);
            }
        });
    }


    public void addNew(View view) {
        Intent intent = new Intent(MainActivity.this,addNew.class);
        startActivity(intent);
    }

    public void deleteRow(View view) {
        final Dialog d = new Dialog(MainActivity.this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.customdialog);
        final EditText et = (EditText) d.findViewById(R.id.et_deleteName);
        Button btn_confirm = (Button) d.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) d.findViewById(R.id.btn_cancel);
        d.show();
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.deleteRow(et.getText().toString());
                Toast.makeText(MainActivity.this, "Data deleted successfully...", Toast.LENGTH_SHORT).show();
                d.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

    }
}

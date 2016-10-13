package com.example.hp.fn_fruits_fregments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class addNew extends Activity {

    EditText et_name,et_type,et_dailyValue,et_grams,et_introduction,et_detail,et_calories;
    Button add_pic,save;
    TextView tv_path;
    String encoded;
    String picturePath;
    private static int RESULT_LOAD_IMAGE = 1;

    FruitDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        dbHelper = new FruitDbHelper(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_type = (EditText) findViewById(R.id.et_type);
        et_dailyValue = (EditText) findViewById(R.id.et_dailyValue);
        et_grams = (EditText) findViewById(R.id.et_grams);
        et_introduction = (EditText) findViewById(R.id.et_introduction);
        et_detail = (EditText) findViewById(R.id.et_details);
        et_calories = (EditText) findViewById(R.id.et_calories);

        add_pic = (Button) findViewById(R.id.btn_addpic);
        save = (Button) findViewById(R.id.btn_save);

        tv_path = (TextView) findViewById(R.id.tv_path);

    }

    public void saveData(View view) {

        String name = et_name.getText().toString();
        String type = et_type.getText().toString();
        int dailyvalue = Integer.parseInt(et_dailyValue.getText().toString());
        int grams = Integer.parseInt(et_grams.getText().toString());
        String introduction = et_introduction.getText().toString();
        String detail = et_detail.getText().toString();
        int calories = Integer.parseInt(et_calories.getText().toString());

        Fruit fruit = new Fruit();
        fruit.setFname(name);
        fruit.setFtype(type);
        fruit.setFval(dailyvalue);
        fruit.setFgrams(grams);
        fruit.setFintro(introduction);
        fruit.setFdetail(detail);
        fruit.setFcal(calories);
        fruit.setFpic(encoded);


        if(dbHelper.insertData(fruit)){
            Toast.makeText(addNew.this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(addNew.this,"Data not inserted",Toast.LENGTH_SHORT).show();
        }
    }

    public void addPic(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            TextView tv_path = (TextView) findViewById(R.id.tv_path);
            tv_path.setText(picturePath);
            cursor.close();

            Bitmap bmp = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = bmp;
            bitmap.compress(Bitmap.CompressFormat.PNG, 30, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Log.d("encoded", encoded);

        }

    }
}


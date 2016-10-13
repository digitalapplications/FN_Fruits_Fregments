package com.example.hp.fn_fruits_fregments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 10/5/2016.
 */
public class FruitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fruits";
    private static final int DATABASE_VERSION = 3;
    SQLiteDatabase database;

    private static final String CREATE_TABLE = "create table fruit (fid integer primary key autoincrement, fpic text, fname text, fcal int, fval int, fintro text, fdetail text, fgrams int,ftype text )";


    public FruitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        Log.d("database", "Table created successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS fruit");
        onCreate(db);
    }

    public boolean insertData(Fruit fruit){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fpic",fruit.getFpic());
        cv.put("fname",fruit.getFname());
        cv.put("fcal",fruit.getFcal());
        cv.put("fval",fruit.getFval());
        cv.put("fintro",fruit.getFintro());
        cv.put("fdetail",fruit.getFdetail());
        cv.put("fgrams", fruit.getFgrams());
        cv.put("ftype",fruit.getFtype());

        long result = database.insert("fruit",null,cv);
        fruit.setFid((int) result);
        return (result!=-1);
    }

    public List<Fruit> retreiveAll(String condition){
        database = this.getWritableDatabase();
        List<Fruit> fruitList = new ArrayList<>();
        String query = "select * from fruit where ftype = '"+condition+"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                Fruit fruit = new Fruit();
                fruit.setFpic(cursor.getString(cursor.getColumnIndex("fpic")));
                Log.d("pic",fruit.getFpic());
                fruit.setFname(cursor.getString(cursor.getColumnIndex("fname")));
                fruit.setFcal(cursor.getInt(cursor.getColumnIndex("fcal")));
                fruit.setFval(cursor.getInt(cursor.getColumnIndex("fval")));
                fruit.setFintro(cursor.getString(cursor.getColumnIndex("fintro")));
                fruit.setFdetail(cursor.getString(cursor.getColumnIndex("fdetail")));
                fruit.setFgrams(cursor.getInt(cursor.getColumnIndex("fgrams")));
                fruit.setFtype(cursor.getString(cursor.getColumnIndex("ftype")));
                fruitList.add(fruit);
            }
        }

        return fruitList;
    }

    public void deleteRow(String name){
        database = this.getWritableDatabase();
        String sql = "delete from fruit where fname = '"+name+"'";
        database.execSQL(sql);
    }
//    public int deleteRow(String name){
//    database = this.getWritableDatabase();
//    String sql = "delete from fruit where fname = '"+name+"'";
//        int result =  database.delete("fruit",name,null);
//        return result;
//}
}

package com.example.hp.fn_fruits_fregments;

import android.os.Bundle;

/**
 * Created by HP on 10/5/2016.
 */
public class Fruit {
    int fid;
    String fpic;
    String ftype;
    String fname;
    int fcal;
    int fval;
    String fintro;
    String fdetail;
    int fgrams;

public Fruit(){

}

    public int getFval() {
        return fval;
    }

    public void setFval(int fval) {
        this.fval = fval;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFpic() {
        return fpic;
    }

    public void setFpic(String fpic) {
        this.fpic = fpic;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFintro() {
        return fintro;
    }

    public void setFintro(String fintro) {
        this.fintro = fintro;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getFgrams() {
        return fgrams;
    }

    public void setFgrams(int fgrams) {
        this.fgrams = fgrams;
    }

    public String getFdetail() {
        return fdetail;
    }

    public void setFdetail(String fdetail) {
        this.fdetail = fdetail;
    }

    public int getFcal() {
        return fcal;
    }

    public void setFcal(int fcal) {
        this.fcal = fcal;
    }

    @Override
    public String toString() {
        return fname;
    }

    public Fruit(Bundle b){
        if(b!=null){
            this.fname = b.getString("fname");
            this.fval = b.getInt("fval");
            this.fcal = b.getInt("fcal");
            this.fdetail = b.getString("fdetail");
            this.fgrams = b.getInt("fgrams");
            this.fintro = b.getString("fintro");
            this.fpic = b.getString("fpic");
            this.ftype = b.getString("ftype");
        }
    }

    public Bundle toBundle(){
        Bundle b = new Bundle();
        b.putString("fname",this.fname);
        b.putInt("fval",this.fval);
        b.putInt("fcal",this.fcal);
        b.putString("fdetail",this.fdetail);
        b.putInt("fgrams",this.fgrams);
        b.putString("fintro",fintro);
        b.putString("fpic",this.fpic);
        b.putString("ftype",this.ftype);
        return b;
    }

}

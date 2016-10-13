package com.example.hp.fn_fruits_fregments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 10/12/2016.
 */

public class FruitListFragment extends ListFragment {

    TextView tv_title;
    List<Fruit> fruits;
    FruitDbHelper dbHelper;
    public callBack activity;
   public FruitListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new FruitDbHelper(getActivity());
        fruits = dbHelper.retreiveAll("cold fruit");
        ArrayAdapter<Fruit> adapter = new customArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,fruits);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fruit_list_fragment,container,false);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(fruit_list_activity.title);
        return rootView;
    }

    public interface callBack{
        public void onItemSelected(Fruit fruit);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Fruit fruit = fruits.get(position);
        activity.onItemSelected(fruit);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (callBack) activity;
    }
}

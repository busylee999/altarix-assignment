package com.busylee.autocheckin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by busylee on 3/16/14.
 */
public class LocationStorageActivity extends FragmentActivity {

    final static String LOCATION_ARRAY = "location_array";

    ArrayList<Location> mLocationArray = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeLocationArray(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LOCATION_ARRAY, mLocationArray);
    }

    private void initializeLocationArray(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mLocationArray = savedInstanceState.getParcelableArrayList(LOCATION_ARRAY);
            return;
        }

        //try get collection from intent
        Intent intent = getIntent();
        if (intent != null) {
            mLocationArray = intent.getParcelableArrayListExtra(LOCATION_ARRAY);
        }

        if (mLocationArray == null)
            mLocationArray = new ArrayList<Location>();
    }

    /**
     * adding new location value
     * @param location
     */
    protected void addNewLocation(Location location){
        mLocationArray.add(location);
    }

    /**
     * getting last coordinate
     * @return
     */
    protected Location getLastLocation(){
        if(mLocationArray != null)
           return mLocationArray.get(mLocationArray.size() - 1);
        return null;
    }

}

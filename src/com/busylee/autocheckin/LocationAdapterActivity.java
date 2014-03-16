package com.busylee.autocheckin;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by busylee on 3/16/14.
 */
public class LocationAdapterActivity extends LocationStorageActivity {

    LocationAdapter mLocationAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        initializeLocationAdapter();
    }

    private void initializeLocationAdapter() {
        if (mLocationAdapter == null)
            mLocationAdapter = new LocationAdapter();
    }

    /**
     * getting adapter
     * @return BaseAdapter
     */
    protected BaseAdapter getLocationAdapter() {
        return mLocationAdapter;
    }

    /**
     * adding new location value and notify adapter
     * @param location
     */
    @Override
    protected void addNewLocation(Location location){
        super.addNewLocation(location);
        mLocationAdapter.notifyDataSetChanged();
    }

    private class LocationAdapter extends BaseAdapter {

        final static int ITEM_LAYOUT_ID = R.layout.location_item_layout;

        LayoutInflater lInflater;

        public LocationAdapter() {
            lInflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mLocationArray.size();
        }

        @Override
        public Object getItem(int i) {
            return mLocationArray.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public Location getLocationByPosition(int position){
            return (Location) getItem(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = lInflater.inflate(ITEM_LAYOUT_ID, parent, false);
            }

            Location location = getLocationByPosition(position);

            ((TextView) view.findViewById(R.id.tvLatitude)).setText(String.valueOf(location.getLatitude()));
            ((TextView) view.findViewById(R.id.tvLongitude)).setText(String.valueOf(location.getLongitude()));

            return view;
        }
    }
}

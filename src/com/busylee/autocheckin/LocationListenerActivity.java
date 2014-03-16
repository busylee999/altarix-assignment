package com.busylee.autocheckin;

import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.widget.Toast;

import java.util.TimerTask;

/**
 * Created by busylee on 3/16/14.
 */
public class LocationListenerActivity extends LocationAdapterActivity implements LocationListener {
    final static String AUTO_START = "auto_start";
    final static int MIN_TIME = 1000 * 10;
    final static int MIN_DISTANCE = 10;

    LocationManager mLocationManager = null;
    boolean mAutoStartListening = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            mAutoStartListening = savedInstanceState.getBoolean(AUTO_START);
        }

        initializeLocationManager();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mAutoStartListening)
            autoStartListening();
    }

    @Override
    public void onPause(){
        super.onPause();
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTO_START, mAutoStartListening);
    }

    private void initializeLocationManager() {
        if (mLocationManager == null)
            mLocationManager = (LocationManager) getSystemService(
                    Context.LOCATION_SERVICE);
    }

    private boolean isProviderEnable(String provider) {
        return mLocationManager.isProviderEnabled(provider);
    }

    private void requestLocationUpdates(String provider) {
        mLocationManager.requestLocationUpdates(
                provider, MIN_TIME, MIN_DISTANCE, this);
    }

    private void autoStartListening(){
        requestLocationUpdates(LocationManager.GPS_PROVIDER);
        requestLocationUpdates(LocationManager.NETWORK_PROVIDER);
    }

    /**
     * start location listening
     */
    public void startLocationListener() {
        mAutoStartListening = true;
        requestLocationUpdates(LocationManager.GPS_PROVIDER);
        requestLocationUpdates(LocationManager.NETWORK_PROVIDER);

        if (!isProviderEnable(LocationManager.GPS_PROVIDER) && !isProviderEnable(LocationManager.NETWORK_PROVIDER))
            Toast.makeText(this,
                    getResources().getString(R.string.gps_provider_error),
                    Toast.LENGTH_LONG)
                    .show();

    }

    /**
     * stop location listening
     */
    public void stopLocationListener() {
        mAutoStartListening = false;
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        addNewLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

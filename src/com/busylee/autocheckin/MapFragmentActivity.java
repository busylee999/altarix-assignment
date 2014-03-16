package com.busylee.autocheckin;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by busylee on 3/16/14.
 */

public class MapFragmentActivity extends LocationStorageActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        GoogleMap map = getMap();

        if (map != null){
            initializePoints(map);
            setCameraToLastLocation(map);
        }

    }

    private GoogleMap getMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        return mapFragment.getMap();
    }

    private void setCameraToLocation(GoogleMap map, Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        map.animateCamera(cameraUpdate);
    }

    private void setCameraToLastLocation(GoogleMap map){
        Location lastLocation = getLastLocation();
        if(lastLocation != null){
            setCameraToLocation(map, lastLocation);
        }
    }

    private void initializePoints(GoogleMap map) {
        if (mLocationArray != null) {
            for (Location location : mLocationArray)
                showLocationOnMap(map, location);
        }
    }

    private void showLocationOnMap(GoogleMap map, Location location) {
        addMarkerToMap(map, location.getLongitude(), location.getLatitude());
    }

    private void addMarkerToMap(GoogleMap map, double longitude, double latitude) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude)));
    }

}

package com.busylee.autocheckin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CoordListActivity extends LocationListenerActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coords);

        initializeViews();

    }

    public void initializeViews(){
        (findViewById(R.id.btnStart)).setOnClickListener(this);
        (findViewById(R.id.btnStop)).setOnClickListener(this);
        (findViewById(R.id.btnShowMap)).setOnClickListener(this);
        ((ListView)findViewById(R.id.lvLocationList)).setAdapter(getLocationAdapter());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btnStart:{
                startLocationListener();
                break;
            }

            case R.id.btnStop:{
                stopLocationListener();
                break;
            }

            case R.id.btnShowMap:{
                Intent intent = new Intent(this, MapFragmentActivity.class);
                intent.putExtra(LocationStorageActivity.LOCATION_ARRAY, mLocationArray);
                startActivity(intent);
                break;
            }
        }
    }

}

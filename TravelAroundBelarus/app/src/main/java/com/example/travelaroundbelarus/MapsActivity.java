package com.example.travelaroundbelarus;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.travelaroundbelarus.databinding.ActivityMapsBinding;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.RectangularBounds;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Places.initialize(getApplicationContext(), "AIzaSyBjI8gwmnvFH1J5_1FeKi7FRebaBx-QVfk");
//        double lat = 53.9045; // Широта Беларуси
//        double lng = 27.5615; // Долгота Беларуси
//        int radius = 50000; // Радиус поиска в метрах
//
//        FindPlaceRequest request = FindPlaceRequest.newInstance("tourist_attraction", FindPlaceRequest.FieldMask.ALL_FIELDS);
//        request.setLocationBias(RectangularBounds.newInstance(
//                LatLng.newInstance(lat - radius / 111000f, lng - radius / 111000f),
//                LatLng.newInstance(lat + radius / 111000f, lng + radius / 111000f)
//        ));

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(53.9045,  27.5615);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Minsk"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
    }
}
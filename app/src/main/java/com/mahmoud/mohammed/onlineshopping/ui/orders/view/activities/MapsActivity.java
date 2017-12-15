package com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.service.GPSTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private String requiredAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Address", requiredAddress);
        setResult(Activity.RESULT_OK, intent);
        finish();
        super.onBackPressed();
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
        GPSTracker gpsTracker=new GPSTracker(this);
        LatLng currentLocation=new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude());

        requiredAddress=getAddressByGpsLocation(currentLocation.latitude, currentLocation.longitude, MapsActivity.this);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title(requiredAddress));
        LatLngBounds.Builder b = new LatLngBounds.Builder();
        b.include(currentLocation);
        LatLngBounds bounds = b.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));

       /* GPSTracker gpsTracker=new GPSTracker(this);
        MarkerOptions markerOptions = new MarkerOptions();
        LatLng currentLocation=new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude());

        markerOptions.position(currentLocation);
        requiredAddress = getAddressByGpsLocation(gpsTracker.getLatitude(),gpsTracker.getLongitude(), MapsActivity.this);
        markerOptions.title(requiredAddress);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.addMarker(markerOptions);*/


      /*  mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                requiredAddress = getAddressByGpsLocation(latLng.latitude, latLng.longitude, MapsActivity.this);
                markerOptions.title(requiredAddress);
                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });*/

    }

    private static String getAddressByGpsLocation(double latitude, double longitude, Context context) {
        Geocoder geocoder;
        List<Address> addresses = new ArrayList<>();
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address = "";

        try {
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

        } catch (Exception e) {

        }
        return address;

    }

    private void zoomToSpan(Marker marker) {
        LatLngBounds.Builder b = new LatLngBounds.Builder();
        b.include(marker.getPosition());
        LatLngBounds bounds = b.build();
        int padding = 15;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
    }

    @Override
    public void onLocationChanged(Location location) {

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

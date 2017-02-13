package com.docto.subham;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapsActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final int ERROR_DIALOG_REQUEST = 1 ;
    GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    PendingResult<LocationSettingsResult> result;
    LocationRequest mLocationRequest;
    final static int REQUEST_LOCATION = 199;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chLocation,14));
        // Fixing Later Map loading Delay
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MapView mv = new MapView(getApplicationContext());
                    mv.onCreate(null);
                    mv.onPause();
                    mv.onDestroy();
                }catch (Exception ignored){

                }
            }
        }).start();

        buildGoogleApiClient();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        mGoogleApiClient.connect();



    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void gotoLocation(double lat,double lng,float zoom) {
        Log.d("value", "gotoLocation called");
        LatLng latLng=new LatLng(lat,lng);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }

    private boolean checkServices() {
        int isAvailable= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isAvailable== ConnectionResult.SUCCESS){
            return true;
        }else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog=GooglePlayServicesUtil.getErrorDialog(isAvailable,this,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Toast.makeText(MapsActivity.this, "Cannot connnect to mapping Service", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap=mapFragment.getMap();
        }
        return (mMap!=null);
    }


    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        //...
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    MapsActivity.this,
                                    REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        //...
                        break;
                }
            }
        });









        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            //getting the latitude value
            double latitudeValue=mLastLocation.getLatitude();
            Log.d("value",""+latitudeValue);
            //getting the longitude value
            double longitudeValue=mLastLocation.getLongitude();
            double ulat=latitudeValue-4.0013569;
            double ulng=longitudeValue+4.0015142;

            if(checkServices()){
                if(initMap()){

                    gotoLocation(latitudeValue,longitudeValue,15);//amri hospital



                    // Other supported types include: MAP_TYPE_NORMAL,
                    // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    mMap.setMyLocationEnabled(true);

                    //Setting up the marker
                    MarkerOptions marker= new MarkerOptions()
                            .title(
                                    "AMRI HOSPITALS," +
                                            "Plot No. 1, Near Jayadev Vatika Park" +
                                            "Khandagiri" +
                                            "Bhubaneswar, Odisha 751019")
                            .position(new LatLng(20.259866,85.777852))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker);
                    MarkerOptions marker1= new MarkerOptions()
                            .title(
                                    "CARE Hospital," +
                                            "Plot No. 324, Unit No. 42, Near Municipal Kalyana Mandapam, Chandrasekharpur," +
                                            "Chandrasekharpur," +
                                            "Bhubaneswar, Odisha 751016")
                            .position(new LatLng(20.330139,85.822545))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker1);
                    MarkerOptions marker2= new MarkerOptions()
                            .title(
                                    "Kalinga Hospital Limited," +
                                            "Shop No. 23, Near Jagannath Temple," +
                                            "Chandrasekharpur," +
                                            "Bhubaneswar, Odisha 751014,")
                            .position(new LatLng(20.3216026,85.8130177))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker2);
                    MarkerOptions marker3= new MarkerOptions()
                            .title(
                                    "Apollo Hospital Heart Institute," +
                                            "Plot No. 251, Old Sainik School Road," +
                                            "Bhubaneswar, Odisha 750015")
                            .position(new LatLng(20.3055911,85.8317877))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker3);
                    MarkerOptions marker4= new MarkerOptions()
                            .title(
                                    "Usthi Hospital," +
                                            "Plot No:-N4-1/1," +
                                            "ID Market Road," +
                                            "IRC Village, Nayapalli," +
                                            "Bhubaneswar, Odisha 751015")
                            .position(new LatLng(20.2934087,85.8113817))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker4);
                    MarkerOptions marker5= new MarkerOptions()
                            .title(
                                    "Ayush Hospital," +
                                            "NH 5" +
                                            "Bhoinagar Basti, Bhoi Nagar," +
                                            "Bhubaneswar, Odisha 751022")
                            .position(new LatLng(20.2962027,85.8340359))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker5);
                    MarkerOptions marker6= new MarkerOptions()
                            .title(
                                    "\n" +
                                            "VIVEKANAND HOSPITAL," +
                                            "A 54/1, Near Fire Station, Fire Station Square," +
                                            "Baramunda," +
                                            "Bhubaneswar, Odisha 751003")
                            .position(new LatLng(20.279207,85.800281))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker6);
                    MarkerOptions marker7= new MarkerOptions()
                            .title(
                                    "CGHS Hospital," +
                                            "Bhauma Nagar," +
                                            "Bhubaneswar, Odisha 751001")
                            .position(new LatLng(20.2794273,85.8292508))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker7);
                    MarkerOptions marker8= new MarkerOptions()
                            .title(

                                    "Pradyumna Bal Memorial Hospital," +
                                            "Kushabhadra Campus, Patia," +
                                            "Bhubaneswar, Odisha 751024,")
                            .position(new LatLng(20.3519386,85.8133424))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker8);
                    MarkerOptions marker9= new MarkerOptions()
                            .title(
                                    "Millenium Hospitals Pvt Ltd," +
                                            "Shop No N1/258, CRP Square," +
                                            "IRC Village, Nayapalli," +
                                            "Bhubaneswar, Odisha 751015")
                            .position(new LatLng(20.285991,20.2859911))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker9);
                    MarkerOptions marker10= new MarkerOptions()
                            .title(
                                    "sum hospital," +
                                            "Kalinga Nagar," +
                                            "Bhubaneswar, Odisha")
                            .position(new LatLng(20.3055911,85.8317877))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
                    mMap.addMarker(marker10);


                    mMap.setTrafficEnabled(true);
                }
            }
        }

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("onActivityResult()", Integer.toString(resultCode));

        //final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode)
        {
            case REQUEST_LOCATION:
                switch (resultCode)
                {
                    case Activity.RESULT_OK:
                    {
                        // All required changes were successfully made
//                        Toast.makeText(MapsActivity.this, "Location enabled by user!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case Activity.RESULT_CANCELED:
                    {
                        // The user was asked to change settings, but chose not to
//                        Toast.makeText(MapsActivity.this, "Location not enabled, user cancelled.", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("GettingLocation", "onConnectionFailed");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("GettingLocation", "onConnectionFailed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}


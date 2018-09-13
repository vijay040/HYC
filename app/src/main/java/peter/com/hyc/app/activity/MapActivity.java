package peter.com.hyc.app.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import peter.com.hyc.R;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String Tag = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSIONS_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    private boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final LatLng APEX = new LatLng(53.44611111, -6.05444444);
    private static final LatLng BALSCADDEN = new LatLng(53.38833333, -6.05444444);
    private static final LatLng CUSH = new LatLng(53.41027778, -6.09083333);
    private static final LatLng DUNBO = new LatLng(53.41222222, -6.06416667);
    private static final LatLng EAST= new LatLng(53.43333333, -6.03666667);
    private static final LatLng GARBH = new LatLng(53.41666667, -6.03861111);
    private static final LatLng HUB= new LatLng(53.42861111, -6.0738889);
    private static final LatLng ISLAND = new LatLng(53.41166667, -6.07277778);
    private static final LatLng THULLA = new LatLng(53.395176, -6.059380);
    private static final LatLng STACK = new LatLng(53.41000000, -6.05194444);
    private static final LatLng MALAHIDE = new LatLng(53.45388889, -6.09305556);
    private static final LatLng NORTH = new LatLng(53.466338889, -6.05666667);
    private static final LatLng OSPREY = new LatLng(53.42361111, -6.05861111);
    private static final LatLng PORTMARNOCK = new LatLng(53.42722222, -6.09666667);
    private static final LatLng ROWAN_ROCKS = new LatLng(53.397893, -6.055039);
    private static final LatLng SOUTH_ROWAN = new LatLng(53.396225, -6.065425);
    private static final LatLng SPIT = new LatLng(53.40583333, -6.07472222);
    private static final LatLng TABLOT = new LatLng(53.45527778, -6.02166667);
    private static final LatLng ULYSSES = new LatLng(53.43694444, -6.08277778);
    private static final LatLng VICEROY = new LatLng(53.41861111, -6.07972222);
    private static final LatLng WEST = new LatLng(53.41611111, -6.10111111);
    private static final LatLng XEBEC = new LatLng(53.40194444, -6.07222222);

    private Marker mApex;
    private Marker mBalscadden;
    private Marker mCush;
    private Marker mDunbo;
    private Marker mEast;
    private Marker mGarbh;
    private Marker mHub;
    private Marker mIsland;
    private Marker mThulla;
    private Marker mStack;
    private Marker mMalahide;
    private Marker mNorth;
    private Marker mOsprey;
    private Marker mPortmarnock;
    private Marker mRowan_rocks;
    private Marker mSouth_rowan;
    private Marker mSpit;
    private Marker mTalbot;
    private Marker mUlysses;
    private Marker mVicroy;
    private Marker mWest;
    private Marker mXebec;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getLocationPermissions();
    }

    private void getDeviceLocation() {
        Log.d(Tag, "getDeviceLocation: getting devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                    return;
                }
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            Log.d(Tag, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);

                        }else {
                            Log.d(Tag, "onComplete: current location is null");
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.d(Tag, "getDeviceLocation: SecurityException:" + e.getMessage());
            Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(Tag, "moveCamera: moving the camera to: lat:" + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void  initMap(){
        Log.d(Tag, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    private void getLocationPermissions() {
        Log.d(Tag, "getLocationPermissions: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();

            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSIONS_REQUEST_CODE);
            }

        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSIONS_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(Tag, "onRequestPermissionsResult: called");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(Tag, "onRequestPermissionResult: permision failed");
                            return;
                        }
                    }
                    Log.d(Tag, "onRequestedPermissionResult: permission granted");
                    mLocationPermissionsGranted = true;

                    initMap();
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        Log.d(Tag, "onMapReady: map is ready");
        mMap = googleMap;
        mMap.setMapType(mMap.MAP_TYPE_HYBRID);

       // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(APEX)
                .title("A")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_a)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(BALSCADDEN)
                .title("B")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_b)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(CUSH)
                .title("C")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_c)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(DUNBO)
                .title("D")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_d)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(EAST)
                .title("E")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_e)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(GARBH)
                .title("G")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_g)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(HUB)
                .title("H")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_h)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(ISLAND)
                .title("I")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_i)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(THULLA)
                .title("J")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_j)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(STACK)
                .title("K")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_k)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(MALAHIDE)
                .title("M")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_m)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(NORTH)
                .title("N")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_n)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(OSPREY)
                .title("O")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_o)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(PORTMARNOCK)
                .title("P")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_p)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(ROWAN_ROCKS)
                .title("Q")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_q)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(SOUTH_ROWAN)
                .title("R")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_r)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(SPIT)
                .title("S")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_s)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(TABLOT)
                .title("T")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_t)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(ULYSSES)
                .title("U")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_u)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(VICEROY)
                .title("V")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_v)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(WEST)
                .title("W")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_w)));
        mApex.setTag(0);

        mApex = mMap.addMarker(new MarkerOptions()
                .position(XEBEC)
                .title("X")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_x)));
        mApex.setTag(0);





        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);

        }
    }
}






package eoghan.projectv2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
/** Allows the user to view all of the stored course on google maps.
 *
 * <p>
 * Citation
 * This class is based on code found at
 * http://stackoverflow.com/questions/14828217/android-map-v2-zoom-to-show-all-the-markers
 * Retrieved 10/04/2017.
 *</p>
 *
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Course Locations");
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     *
     * @param googleMap GoogleMap the displays the map when the map is ready to display the markers.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add markers
        LatLng beechpark = new LatLng(53.2600, -6.4981);
        LatLng castleknock = new LatLng(53.3658, -6.3954);
        LatLng moyvalley = new LatLng(53.4298, -6.9208);
        LatLng kclub = new LatLng(53.3066, -6.6253);
        LatLng dunmurry = new LatLng(53.1894, -6.9333);
        LatLng portarlington = new LatLng(53.1465, -7.2381);
        LatLng blessingtonlakes = new LatLng(53.1244, -6.5403);
        LatLng grangecastle = new LatLng(53.3153, -6.4326);

        // Set marker titles
        mMap.addMarker(new MarkerOptions().position(beechpark).title("Beechpark Golf Club"));
        mMap.addMarker(new MarkerOptions().position(castleknock).title("Castleknock Golf Club"));
        mMap.addMarker(new MarkerOptions().position(moyvalley).title("Moyvalley Golf Club"));
        mMap.addMarker(new MarkerOptions().position(kclub).title("The K Club Golf & Country Club"));
        mMap.addMarker(new MarkerOptions().position(dunmurry).title("Dunmurry Springs Golf Club"));
        mMap.addMarker(new MarkerOptions().position(portarlington).title("Portarlington Golf Club"));
        mMap.addMarker(new MarkerOptions().position(blessingtonlakes).title("Blessington Lakes Golf Club"));
        mMap.addMarker(new MarkerOptions().position(grangecastle).title("Grange Castle Golf Club"));

        // Set markers as bounds for camera view
        LatLngBounds courseBounds = new LatLngBounds.Builder()
                .include(beechpark)
                .include(castleknock)
                .include(moyvalley)
                .include(kclub)
                .include(dunmurry)
                .include(portarlington)
                .include(blessingtonlakes)
                .include(grangecastle)
                .build();

        // Adds 10% space around the bounds to ensure no markers are on the edge
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int space = (int) (width * 0.10);

        // Sets camera
        CameraUpdate courses = CameraUpdateFactory.newLatLngBounds(courseBounds, width, height, space);

        // Shows markers on map
        mMap.animateCamera(courses);
    }


}

package assignment4.jcnelson.geotexter;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions marker;
    private LatLng loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Button button = (Button) findViewById(R.id.sendLocButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LatLng loc = marker.getPosition();
                System.out.println("Button Pressed!");

                try {
                    sendSmsMessage("5073589138", loc.toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Unable to send SMS", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }            }
        });

    }

    private void onSendLocButtonClick(View view){


        try {
            sendSmsMessage("5073589138", loc.toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Unable to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void sendSmsMessage(String address, String message) throws Exception{
        Toast.makeText(getApplicationContext(), "Sent: " + message + " to: " + address, Toast.LENGTH_LONG).show();
        SmsManager smsMgr = SmsManager.getDefault();
        smsMgr.sendTextMessage(address, null, message, null, null);
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
        loc = new LatLng(-34, 151);
        marker = new MarkerOptions().title("Loc");
        marker.draggable(true);
        marker.position(loc);
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }
}

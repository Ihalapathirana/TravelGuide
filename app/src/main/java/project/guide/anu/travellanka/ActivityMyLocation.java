package project.guide.anu.travellanka;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

import project.guide.anu.travellanka.controllerpackage.AreaDistance;


public class ActivityMyLocation extends ActionBarActivity {

    //variables for map
    private MapView myOpenMapView;
    private MapController myMapController;
    ArrayList<OverlayItem> anotherOverlayItemArray;
    MyLocationNewOverlay myLocationOverlay;

    double latiOriginal;
    double longiOriginal;
    String locationOriginal;

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_my_location);
        /**set openstreetmap in to mapView in second tab**/
        myOpenMapView = (MapView) findViewById(R.id.map);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setMultiTouchControls(true);

        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        locationOriginal= (String) my_bundle_received.get("longi and latitu");

        try {
            String s1 = locationOriginal.replaceAll("Latitude ", "");
            String s2 = locationOriginal.replaceAll("Longitude", "");

            String[] splitLocation1 = s1.split(",");
            String[] splitLocation2 = s2.split(",");
            latiOriginal = Double.parseDouble(splitLocation1[0]);
            longiOriginal = Double.parseDouble(splitLocation2[1]);
        }
        catch (Exception e){
            if(locationOriginal.equals("unassigned"))
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Wait for GPS location...");

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        EnableGPSIfPossible();
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }

        myMapController = (MapController) myOpenMapView
                .getController();
        myMapController.setZoom(12);

        /**set the current location of the user
         * to the OpenStreetMap. **/
        GeoPoint center = new GeoPoint(latiOriginal, longiOriginal);
        myMapController.animateTo(center);


        /**initialize the overlayItem array list to
         * create multi markers in the map**/
        anotherOverlayItemArray = new ArrayList<OverlayItem>();


        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay
                = new ItemizedIconOverlay<OverlayItem>(
                this, anotherOverlayItemArray, myOnItemGestureListener);
        myOpenMapView.getOverlays().add(anotherItemizedIconOverlay);

        /**Add Scale Bar to the OpenStreetMap **/
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        myOpenMapView.getOverlays().add(myScaleBarOverlay);

        /**Add MyLocationOverlay to the map**/
        myLocationOverlay = new MyLocationNewOverlay(this, myOpenMapView);

        myOpenMapView.getOverlays().add(myLocationOverlay);
        myOpenMapView.postInvalidate();
        System.out.println(myLocationOverlay.getLastFix() + " anu Location");
        CompassOverlay com = new CompassOverlay(this, myOpenMapView);
        myOpenMapView.getOverlays().add(com);
    }

    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener
            = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            Toast.makeText(ActivityMyLocation.this,
                    item.getTitle() + "\n" + "Distance: " + item.getSnippet() + " km",
                    Toast.LENGTH_LONG).show();
            return true;
        }

    };

    /**
     * to get user's current location *
     */
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        myLocationOverlay.enableMyLocation();

        System.out.println("enable");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        myLocationOverlay.disableMyLocation();

        System.out.println("desable");
    }

    private void EnableGPSIfPossible()
    {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
    }

    private  void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Yout GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();

    }
}


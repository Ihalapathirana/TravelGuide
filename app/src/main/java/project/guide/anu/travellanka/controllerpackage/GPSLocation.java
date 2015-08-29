package project.guide.anu.travellanka.controllerpackage;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Anu on 7/28/2015.
 */
public class GPSLocation implements LocationListener {

    protected LocationManager locationManager;

    Context context;
    String latlng="unassigned";
    public GPSLocation(Context context){
        this.context = context;

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);


       }
    @Override
    public void onLocationChanged(Location location) {
        latlng ="Latitude "+location.getLatitude()+", Longitude"+location.getLongitude();
       // Toast.makeText(context,"onLocationChanged",Toast.LENGTH_SHORT).show();
        Log.d("lati and long ",latlng);
    }

    public void requestUpdate(){
        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.ACCURACY_FINE);

        String best = locationManager.getBestProvider(crit, false);
        locationManager.requestLocationUpdates(best, 0, 100f, this);
    }
    public String getLatLng(){
        return latlng;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void removeUpdates(){
        locationManager.removeUpdates(this);
    }
}

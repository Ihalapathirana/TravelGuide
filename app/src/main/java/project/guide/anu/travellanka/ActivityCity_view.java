package project.guide.anu.travellanka;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivityCity_view extends ActionBarActivity {

    City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);

        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        city= (City) my_bundle_received.get("cityObject");


        TextView name= (TextView) findViewById(R.id.city_name);
        ImageView image= (ImageView) findViewById(R.id.city_image);
        TextView description= (TextView) findViewById(R.id.city_description);



        Log.d("name = second activity",city.getCity_name());

        name.setText(city.getCity_name());
        image.setImageResource(city.getCity_image());
        description.setText(city.getCity_description());
    }

    public void downloadButton(View v){
        double lati;
        double lon;
        GPSTracker gpsTracker=new GPSTracker(this);
        lati = gpsTracker.getLatitude();
        lon=gpsTracker.getLongitude();

        Log.d("lati and long",String.valueOf(lati));
        Log.d("lati and long",String.valueOf(lon));

    }
}

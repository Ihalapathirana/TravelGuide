package project.guide.anu.travellanka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityCity_view extends ActionBarActivity {

    City city;
    GPSLocation gpsTracker;
    Button b;
    ImageView image;
    TextView name;
    TextView description;
    Button buttonDownload;

    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);

        //get data from bundle
        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        city= (City) my_bundle_received.get("cityObject");
//        gpsTracker=new GPSLocation(ActivityCity_view.this);

        //initialize
         name= (TextView) findViewById(R.id.city_name);
         image= (ImageView) findViewById(R.id.city_image);
         description= (TextView) findViewById(R.id.city_description);
         buttonDownload = (Button)findViewById(R.id.button);



        Log.d("name = second activity",city.getCity_name());
        //set city data
        name.setText(city.getCity_name());
        image.setImageResource(city.getCity_image());
        description.setText(city.getCity_description());
    }

    public void downloadButton(View v){
        //toast for gps location
        Toast.makeText(ActivityCity_view.this, "unassign", Toast.LENGTH_SHORT).show();

    }
    //gps location
    @Override
    protected void onResume() {
        super.onResume();
     //   gpsTracker.requestUpdate();
    }

    @Override
    protected void onPause() {
        super.onPause();
     //   gpsTracker.removeUpdates();
    }
}

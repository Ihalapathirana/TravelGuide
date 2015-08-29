package project.guide.anu.travellanka.viewpackage;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import project.guide.anu.travellanka.adapterpackage.CustomSwipeAdapter;
import project.guide.anu.travellanka.R;
import project.guide.anu.travellanka.modelpackage.City;


public class ActivityFirst extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    Timer swipeTimer;
    ViewPager viewPager;
    CustomSwipeAdapter adapterSwipe;

    String name;
    int image;
    String desc;
    String fileName;
    City city;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_first);
        // this.startActivity(getIntent());


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapterSwipe = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapterSwipe);

        // you need to have a list of data that you want the spinner to display
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Select cities");
        spinnerArray.add("Colombo");
        spinnerArray.add("Galle");
        spinnerArray.add("Kandy");
        spinnerArray.add("Hikkaduwa");
        spinnerArray.add("Trinkomalee");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.planets_spinner);
        sItems.setAdapter(adapter);
        sItems.setOnItemSelectedListener(ActivityFirst.this);




        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            int i = 0;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(i % 5);
                        i++;
                    }
                });
            }
        }, 500, 5000);
    }

    public void next(View view){
        Intent i=new Intent(ActivityFirst.this,ActivityCitySelection.class);
        startActivity(i);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {






    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

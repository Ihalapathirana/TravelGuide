package project.guide.anu.travellanka;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


public class ActivityFirst extends ActionBarActivity {

    Timer swipeTimer;
    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_first);
        this.startActivity(getIntent());



    viewPager= (ViewPager) findViewById(R.id.viewPager);
    adapter=new CustomSwipeAdapter(this);
    viewPager.setAdapter(adapter);

    swipeTimer = new Timer();
    swipeTimer.schedule(new TimerTask() {

        int i=0;
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(i%5);
                    i++;
                }
            });
        }
    }, 500, 3000);
}

    public void next(View view){
        Intent i=new Intent(ActivityFirst.this,ActivityCitySelection.class);
        startActivity(i);
    }
}

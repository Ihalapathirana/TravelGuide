package project.guide.anu.travellanka;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class ActivityCover extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_cover);
        this.startActivity(getIntent());
        next();

    }


    public void next(){
        Thread logoTimer = new Thread(){
            @Override
            public void run() {

                try {

                    sleep(2500);

                    Intent i = new Intent(ActivityCover.this,ActivityCitySelection.class);

                    startActivity(i);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                } finally {

                    finish();
                }

            }

        };

        logoTimer.start();
    }
}
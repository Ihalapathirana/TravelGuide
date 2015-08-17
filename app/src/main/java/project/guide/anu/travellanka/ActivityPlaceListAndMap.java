package project.guide.anu.travellanka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;


public class ActivityPlaceListAndMap extends ActionBarActivity {

    SeachPlace sPlace;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list_and_map);
        Button button= (Button) findViewById(R.id.button2);

        ArrayList<SeachPlace> placeList = new ArrayList<>();
        //add data to list view
        placeList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "hotel@gmail.com", R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "hotel@gmail.com", R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "hotel@gmail.com", R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "hotel@gmail.com", R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "hotel@gmail.com", R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "hotel@gmail.com", R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "hotel@gmail.com", R.drawable.hikkaduwa));

        //set list view to the adapter
        MyPlaceListAddapter myAdapter = new MyPlaceListAddapter(ActivityPlaceListAndMap.this, placeList);
        listView = (ListView) findViewById(R.id.place_list_view);
        listView.setAdapter(myAdapter);

        //set tabhost
        TabHost tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec spec1 = tabhost.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Hotel", null);
        tabhost.addTab(spec1);

        TabHost.TabSpec spec2 = tabhost.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Map", null);
        tabhost.addTab(spec2);
    }
        //onclick method in suggestion button
        public void suggestPage(View v){

        Intent i=new Intent(ActivityPlaceListAndMap.this,ActivitySuggestions.class);
        startActivity(i);
         }



    }




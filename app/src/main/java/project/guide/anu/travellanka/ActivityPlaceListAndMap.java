package project.guide.anu.travellanka;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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


        ArrayList<SeachPlace> placeList=new ArrayList<>();
   /*     String[] values = new String[] { "light house",
                "Galadhari",
                "Hilton",
                "anusha",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        ListView listView= (ListView) findViewById(R.id.place_list_view);

        listView.setAdapter(adapter);*/

        placeList.add(new SeachPlace("    Colombo","50,sda,asd","0714658236","anushakdsal",R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo","50,sda,asd","0714658236","anushakdsal",R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo","50,sda,asd","0714658236","anushakdsal",R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo","50,sda,asd","0714658236","anushakdsal",R.drawable.hikkaduwa));
        placeList.add(new SeachPlace("    Colombo","50,sda,asd","0714658236","anushakdsal",R.drawable.hikkaduwa));

        MyPlaceListAddapter myAdapter = new MyPlaceListAddapter(ActivityPlaceListAndMap.this,placeList);
        listView=(ListView)findViewById(R.id.place_list_view);
        listView.setAdapter(myAdapter);

        TabHost tabhost= (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec spec1=tabhost.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Hotel",null);
        tabhost.addTab(spec1);

        TabHost.TabSpec spec2=tabhost.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Map",null);
        tabhost.addTab(spec2);



    }


}

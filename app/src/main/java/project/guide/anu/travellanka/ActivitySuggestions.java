package project.guide.anu.travellanka;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;


public class ActivitySuggestions extends ActionBarActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_suggestions);


        ArrayList<SeachPlace> suggestList=new ArrayList<>();
        //add data to suggetstion list view
        suggestList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "", R.drawable.hikkaduwa));
        suggestList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "", R.drawable.hikkaduwa));
        suggestList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "", R.drawable.hikkaduwa));
        suggestList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "", R.drawable.hikkaduwa));
        suggestList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "", R.drawable.hikkaduwa));
        suggestList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "", R.drawable.hikkaduwa));
        suggestList.add(new SeachPlace("    Colombo", "50,colombo,Sri lanka", "0714658236", "", R.drawable.hikkaduwa));

        //set list view to adapter
        MyPlaceListAddapter myAdapter = new MyPlaceListAddapter(ActivitySuggestions.this,suggestList);
        listView=(ListView)findViewById(R.id.sugListView);
        listView.setAdapter(myAdapter);
    }



}

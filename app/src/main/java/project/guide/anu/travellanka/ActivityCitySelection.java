package project.guide.anu.travellanka;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ActivityCitySelection extends ActionBarActivity {

    ListView listView;
    City city;
    String name;
    int image;
    String desc;
    GPSLocation gpsTracker;
    Button button;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selection);
//        gpsTracker = new GPSLocation(ActivityCitySelection.this);
        button= (Button) findViewById(R.id.downloadButton);

        //progress dialog for downloading
        mProgressDialog = new ProgressDialog(ActivityCitySelection.this);
        mProgressDialog.setMessage("Downloading...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);


        ArrayList<City> cityList=new ArrayList<>();
        //add data to city list
        cityList.add(new City("    Colombo", R.drawable.colombo));
        cityList.add(new City("    Galle", R.drawable.galle));
        cityList.add(new City("    Kandy", R.drawable.kandy));
        cityList.add(new City("    Hikkaduwa", R.drawable.hikkaduwa));
        cityList.add(new City("    Trincomalee", R.drawable.trinko));

        // set list view to the adapter
        MyCityListAdapter myAdapter = new MyCityListAdapter(ActivityCitySelection.this,cityList);
        listView=(ListView)findViewById(R.id.citylistView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long _id){


                Log.d("Anusha","p"+position);
            //check cases
                switch(position){
                    case 0:
                        name="Colombo";
                        image= R.drawable.colombo;
                        desc="★ Colombo is the commercial capital and largest city of Sri Lanka. "+"\n"+"★ Colombo has a population of 5.6 million metropolitan area  and 555,031 in the City proper."+"\n"+"★ Galle Face Green is a ribbon of green space located in the heart of the city along the Indian Ocean coast, and is a popular destination for tourists and residents alike. "+"\n"+"★ The Galle Face Hotel is a historic landmark on the southern edge of this promenade. "+"\n"+"★ Gangaramaya Temple is one of the most important temples in Colombo. ";
                        break;

                    case 1:
                        name="Galle";
                        image= R.drawable.galle;
                        desc="★ Galle is a major city in Sri Lanka, situated on the southwestern tip of Sri Lanka, 119 km from Colombo."+"\n"+"★ Galle is the administrative capital of Southern Province, Sri Lanka and is the district capital of Galle."+"\n"+"★ Galle is the fourth largest city in Sri Lanka after the capital Colombo, Kandy and Jaffna."+"\n"+"★ Important natural geographical features in Galle include Rumassala in Unawatuna, a large mound-like hill, which forms the eastern protective barrier to the Galle harbor."+"\n"+"★ Other prominent landmarks in Galle include"+"\n"+"       ♦ city's natural harbor"+"\n"+"       ♦ the National Maritime Museum"+"\n"+"       ♦ St. Mary's Cathedral "+"\n"+"       ♦ Amangalla the historic luxury hotel.";
                        break;

                    case 2:
                        name="Kandy";
                        image= R.drawable.kandy;
                        desc="★ Kandy is a major city in Sri Lanka, located in the Central Province, Sri Lanka."+"\n"+"★ It is the second largest city in the country after Colombo. "+"\n"+"★ The city lies in the midst of hills in the Kandy plateau, which crosses an area of tropical plantations, mainly tea."+"\n"+"★ Kandy is both an administrative and religious city and is also the capital of the Central Province."+"\n"+"★ Kandy is the home of The Temple of the Tooth Relic, one of the most sacred places of worship in the Buddhist world. "+"\n"+"★It was declared a world heritage site by UNESCO.";
                        break;

                    case 3:
                        name="Hikkaduwa";
                        image= R.drawable.hikkaduwa;
                        desc="★ Hikkaduwa is a small town on the south coast of Sri Lanka located in the Southern Province, about 17 km north-west of Galle and 98 km south of Colombo."+"\n"+"★ Hikkaduwa's beach and night life make it a popular tourist destination."+"\n"+"★ It is a well-known international destination for board-surfing."+"\n"+"★ Hikkaduwa’s overexploited ‘coral sanctuary’ stretches out from the string of ‘Coral’ hotels at the north end of the strip to a group of rocks a couple of hundred metres offshore" ;

                        break;

                    case 4:
                        name="Trincomalee";
                        image= R.drawable.trinko;
                        desc="★ Trincomalee is the administrative headquarters of the Trincomalee District and major resort port city of Eastern Province, Sri Lanka. "+"\n"+"★ Located on the east coast of the island overlooking the Trincomalee Harbour, 113 miles south of Jaffna and 69 miles north of Batticaloa, Trincomalee has been one of the main centres of Tamil language speaking culture on the island for over two millennia. ";
                        break;

                    default:
                        name=null;
                        image=0;
                        desc=null;
                        break;
                }




                //add data to bundle for send it to the next intent
                city=new City(name,image,desc);
                Bundle simple_bundle=new Bundle();
                simple_bundle.putParcelable("cityObject",city);

                Intent intent=new Intent(ActivityCitySelection.this,ActivityCity_view.class);
                intent.putExtras(simple_bundle);
                startActivity(intent);

            }
        });


    }

   public void download(View view){
        //.sql file download code here

        final DownloadTask downloadTask = new DownloadTask(ActivityCitySelection.this,mProgressDialog);
        downloadTask.execute("https://www.dropbox.com/s/f6colmypsy3cksq/galle%5B2%5D.sql?dl=0");



    }



}


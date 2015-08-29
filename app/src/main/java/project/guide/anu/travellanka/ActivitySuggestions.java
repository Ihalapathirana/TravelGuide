package project.guide.anu.travellanka;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ActivitySuggestions extends ActionBarActivity {

    ListView listView;
    SeachPlace sPlace;
    TextView tv;

    String navigationPosition;
    String crString;
    ArrayList<SeachPlace> placeList;
    ArrayList<String> web;
    ArrayList<String> name;
    ArrayList<String> tele;
    ArrayList<String> desc;
    ArrayList<String> addre;
    ArrayList<Integer> image;


    String place_id;
    String place_name;
    String place_address;
    String place_location;
    String place_webSiteLink;
    String place_description;
    String place_telephone;


    //for database
    UserDBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context con;
    Cursor cr;


    //tati n longi
    double latiOriginal ;
    double longiOriginal;
    AreaDistance cordinates;
    double distance;
    String locationOriginal;
    int i=0;
    static ArrayList<String> longitude = new ArrayList<>();
    static ArrayList<String> latitute = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_suggestions);


        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        navigationPosition= (String) my_bundle_received.get("naviDrawer");
        locationOriginal= (String) my_bundle_received.get("lati and longi");
        String s1 = locationOriginal.replaceAll("Latitude ","");
        String s2 = locationOriginal.replaceAll("Longitude","");
        String[] splitLocation1 = s1.split(",");
        String[] splitLocation2= s2.split(",");
        latiOriginal= Double.parseDouble(splitLocation1[0]);
        longiOriginal= Double.parseDouble(splitLocation2[1]);


        /**Initialize the context
         * create UserDBHelper object and call database**/
        con = this;
        dbHelper = new UserDBHelper(con);
        sqLiteDatabase = dbHelper.getWritableDatabase();

         tv= (TextView) findViewById(R.id.textView);


        cordinates=new AreaDistance();
        placeList = new ArrayList<>();
        web= new ArrayList<>();
        name=new ArrayList<>();
        addre= new ArrayList<>();
        tele=new ArrayList<>();
        desc= new ArrayList<>();
        image=new ArrayList<>();

        getDataBase();

        //set list view to the adapter
        MyPlaceListAddapter myAdapter = new MyPlaceListAddapter(ActivitySuggestions.this, placeList);
        listView = (ListView) findViewById(R.id.sugListView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long _id){


                /**add city data to city object and send that object to the city_View class to
                 display the city details using bundle **/


                SeachPlace sPlace2=new SeachPlace(name.get(position),addre.get(position),tele.get(position),web.get(position),desc.get(position),1);
                Bundle simple_bundle=new Bundle();
                simple_bundle.putParcelable("searchPlace", sPlace2);

                Intent intent=new Intent(ActivitySuggestions.this, ActivityPlaceDetails.class);
                intent.putExtras(simple_bundle);
                startActivity(intent);

            }
        });



    }

    /**get data from database by using Cursor object
     * get data according to users selection category **/
    public void getDataBase() {
/** Do specific suggestion operations according to category **/
 if(navigationPosition.equals("hotel")){
/** get data to cursor from the getBankDataFromDatabase method using DBHelper object **/
     cr = dbHelper.getRestaurantDataFromDatabase(dbHelper);
     /**Move cursor to the first column in the database**/
     cr.moveToFirst();
     crString=cr.getColumnName(0);
     if( crString.equals("rest_id")) {
         /**Iterate until get last column of the database **/
         do {
             /** add column data to the suitable variables**/
             place_id=cr.getString(0);
             place_name=cr.getString(2);
             place_address=cr.getString(3);
             place_location=cr.getString(4);
             place_webSiteLink=cr.getString(5);
             place_telephone=cr.getString(6);

             /** print the output in the console **/
             System.out.println("ID " + cr.getString(0));
             System.out.println("name " + cr.getString(2));
             System.out.println("address " + cr.getString(3));
             System.out.println("location " + cr.getString(4));
             System.out.println(navigationPosition + " Restaurant");

             /** In database latitute and longitude contains as one String
              *  split location string and get longitude and latitude as double values**/
             String[] s = place_location.split(",");

             /** add those latitude and longitude values to seperate array lists **/
             latitute.add(s[0]);
             longitude.add(s[1]);

             /** print the output in the console **/
             System.out.println(latitute.get(i) + " lati of " + i);
             System.out.println(longitude.get(i) + " Long of " + i);
             /** get the distance between user and place using ArrayDistance class object **/
             distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
             /**Moe to the next column in the database**/
             i++;

             tv.setText("Near Restaurants");

             /** check whether distance is less than 8km
              * if --> less == add related data in to specific arraylists to each data
              * else --> do nothing. ignore the place **/
             if (distance <= 8) {
                 sPlace=new SeachPlace(place_name,place_address,place_telephone,place_webSiteLink,"",R.drawable.rest);
                 placeList.add(sPlace);
                 System.out.println("Distance " + distance + " to " + i);

                 name.add(place_name);
                 web.add(place_webSiteLink);
                 addre.add(place_address);
                 tele.add(place_telephone);
                 desc.add("");
             } else {
                 System.out.println("out or area "+ distance);
             }


         } while (cr.moveToNext());


     }
}
else if(navigationPosition.equals("restaurant")){

     cr = dbHelper.getHotelDataFromDatabase(dbHelper);
     cr.moveToFirst();
     crString=cr.getColumnName(0);
     if( crString.equals("hotel_id")) {
         do {

             place_id=cr.getString(0);
             place_name=cr.getString(2);
             place_address=cr.getString(3);
             place_location=cr.getString(4);
             place_webSiteLink=cr.getString(5);
             place_telephone=cr.getString(6);
             place_description=cr.getString(7);


             System.out.println("ID " + cr.getString(0));
             System.out.println("name " + cr.getString(2));
             System.out.println("address " + cr.getString(3));
             System.out.println("location " + cr.getString(4));
             System.out.println(navigationPosition + " Hotel");

             String[] s = place_location.split(",");

             latitute.add(s[0]);
             longitude.add(s[1]);

             System.out.println(latitute.get(i) + " lati of " + i);
             System.out.println(longitude.get(i) + " Long of " + i);
             distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
             i++;

             tv.setText("Near Hotels");

             if (distance <= 8) {
                 sPlace=new SeachPlace(place_name,place_address,place_telephone,place_webSiteLink,place_description,R.drawable.hotel);
                 placeList.add(sPlace);
                 System.out.println("Distance " + distance + " to " + i);

                 name.add(place_name);
                 web.add(place_webSiteLink);
                 addre.add(place_address);
                 tele.add(place_telephone);
                 desc.add(place_description);
             } else {
                 System.out.println("out or area "+ distance);
             }


         } while (cr.moveToNext());


     }
        }

        else if(navigationPosition.equals("train_station")){
     cr = dbHelper.getBusStopDataFromDatabase(dbHelper);
     cr.moveToFirst();
     crString=cr.getColumnName(0);
     if( crString.equals("busstop_id")) {
         do {

             place_id=cr.getString(0);
             place_name=cr.getString(2);
             place_address=cr.getString(3);
             place_location=cr.getString(4);




             System.out.println("ID " + cr.getString(0));
             System.out.println("name " + cr.getString(2));
             System.out.println("address " + cr.getString(3));
             System.out.println("location " + cr.getString(4));
             System.out.println(navigationPosition + " busstop");
             String[] s = place_location.split(",");

             latitute.add(s[0]);
             longitude.add(s[1]);

             System.out.println(latitute.get(i) + " lati of " + i);
             System.out.println(longitude.get(i) + " Long of " + i);
             distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
             i++;

             tv.setText("Near Bus Stops");

             if (distance <= 8) {
                 sPlace=new SeachPlace(place_name,place_address," "," ","",R.drawable.bus);
                 placeList.add(sPlace);
                 System.out.println("Distance " + distance + " to " + i);
                 name.add(place_name);
                 web.add("");
                 addre.add(place_address);
                 tele.add("");
                 desc.add("");
             } else {
                 System.out.println("out or area "+ distance);
             }


         } while (cr.moveToNext());


     }
        }

        else if(navigationPosition.equals("bus_stop")){

     cr = dbHelper.getTrainStationDataFromDatabase(dbHelper);
     cr.moveToFirst();
     crString=cr.getColumnName(0);
     if( crString.equals("train_station_id")) {
         do {
             place_id=cr.getString(0);
             place_name=cr.getString(2);
             place_address=cr.getString(3);
             place_location=cr.getString(4);


             System.out.println("ID " + cr.getString(0));
             System.out.println("name " + cr.getString(2));
             System.out.println("address " + cr.getString(3));
             System.out.println("location " + cr.getString(4));
             System.out.println(navigationPosition + " train_station");

             String[] s = place_location.split(",");

             latitute.add(s[0]);
             longitude.add(s[1]);


             System.out.println(latitute.get(i) + " lati of " + i);
             System.out.println(longitude.get(i) + " Long of " + i);
             distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
             i++;

             tv.setText("Near Train Stations");

             if (distance <= 8) {
                 sPlace=new SeachPlace(place_name,place_address," "," ","",R.drawable.train);
                 placeList.add(sPlace);
                 System.out.println("Distance " + distance + " to " + i);
                 name.add(place_name);
                 web.add("");
                 addre.add(place_address);
                 tele.add("");
                 desc.add("");
             } else {
                 System.out.println("out or area "+ distance);
             }


         } while (cr.moveToNext());


     }

  }

else {
     tv.setText("No suggestions to chosen category");
        }

    }
}

package project.guide.anu.travellanka.viewpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

import project.guide.anu.travellanka.controllerpackage.AreaDistance;
import project.guide.anu.travellanka.adapterpackage.MyPlaceListAddapter;
import project.guide.anu.travellanka.R;
import project.guide.anu.travellanka.controllerpackage.GPSLocation;
import project.guide.anu.travellanka.modelpackage.SeachPlace;
import project.guide.anu.travellanka.databasehandler.UserDBHelper;


public class ActivityPlaceListAndMap extends ActionBarActivity{

    SeachPlace sPlace;
    ListView listView;
    String navigationPosition;
    String crString;
    ArrayList<SeachPlace> placeList;
    ArrayList<String> web;
    ArrayList<String> name;
    ArrayList<String> tele;
    ArrayList<String> desc;
    ArrayList<String> addre;
    ArrayList<Integer> image;
    ArrayList<String> location;
    ArrayList<Double> distanceArray;
   

    String place_id;
    String place_name;
    String place_address;
    String place_location;
    String place_webSiteLink;
    String place_description;
    String place_telephone;


    // variables for database
    UserDBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context con;
    String fileName;
    Cursor cr;

    //variables for map
    private MapView myOpenMapView;
    private MapController myMapController;
    LocationManager locationManager;
    ArrayList<OverlayItem> overlayItemArray;
    ArrayList<OverlayItem> anotherOverlayItemArray;
    MyLocationNewOverlay myLocationOverlay;


    //variables for find distance
    double latiOriginal;
    double longiOriginal;
    String locationOriginal;
    AreaDistance cordinates;
    double distance;
    int i=0;
    GPSLocation gpsTracker;
    static ArrayList<String> longitude = new ArrayList<>();
    static ArrayList<String> latitute = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list_and_map);
        Button button= (Button) findViewById(R.id.button2);

        /** get the choice of user from navigation drawer
         * and the position of the navigation drawer get using bundle**/
        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        navigationPosition= (String) my_bundle_received.get("NaviPosition");
        locationOriginal= (String) my_bundle_received.get("longi and latitu");
        fileName=(String)my_bundle_received.get("file");

        if(locationOriginal.equals("unassigned"))
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Wait for GPS location...");

            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    EnableGPSIfPossible();
                }
            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else {
            Toast.makeText(this,navigationPosition+"  "+locationOriginal,Toast.LENGTH_SHORT).show();
            String s1 = locationOriginal.replaceAll("Latitude ","");
            String s2 = locationOriginal.replaceAll("Longitude","");
            System.out.println(navigationPosition + " IN ACTIVITY MAP");

            String[] splitLocation1 = s1.split(",");
            String[] splitLocation2= s2.split(",");
            latiOriginal= Double.parseDouble(splitLocation1[0]);
            longiOriginal= Double.parseDouble(splitLocation2[1]);

            System.out.println(splitLocation1[0]+ "lati lang anusha "+ splitLocation2[1]);
        }


        /**Initialize the context
         * create UserDBHelper object and call database**/
        con = this;
        dbHelper = new UserDBHelper(con);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        /** initialize arraylists**/
        cordinates=new AreaDistance();
        placeList = new ArrayList<>();
        web= new ArrayList<>();
        name=new ArrayList<>();
        addre= new ArrayList<>();
        tele=new ArrayList<>();
        desc= new ArrayList<>();
        image=new ArrayList<>();
        location=new ArrayList<>();
        distanceArray =new ArrayList<>();


        /**Call method to get data from database**/

        getDataBase();



        //set list view to the adapter
        MyPlaceListAddapter myAdapter = new MyPlaceListAddapter(ActivityPlaceListAndMap.this, placeList);
        listView = (ListView) findViewById(R.id.place_list_view);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long _id){


                /**add city data to city object and send that object to the city_View class to
                 display the city details using bundle **/


                SeachPlace sPlace2=new SeachPlace(name.get(position),addre.get(position),tele.get(position),web.get(position),desc.get(position),1);
                Bundle simple_bundle=new Bundle();
               simple_bundle.putParcelable("searchPlace", sPlace2);

                System.out.println(desc.get(position)+" Anusha desc");
                Intent intent=new Intent(ActivityPlaceListAndMap.this, ActivityPlaceDetails.class);
                intent.putExtras(simple_bundle);
                startActivity(intent);

            }
        });





    /** initialize the two tabs  **/
        TabHost tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec spec1 = tabhost.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        /**  set the title of the tab according to the user chosen category **/
        if(navigationPosition.equals("bank")){
            spec1.setIndicator("Bank", null);
        }
        else if(navigationPosition.equals("hotel")){
            spec1.setIndicator("Hotel", null);
        }
        else if(navigationPosition.equals("gas_station")){
            spec1.setIndicator("Gas Filling Station", null);
        }
        else if(navigationPosition.equals("historical_places")){
            spec1.setIndicator("Attraction Places", null);
        }
        else if(navigationPosition.equals("bus_stop")){
            spec1.setIndicator("Bus stops", null);
        }
        else if(navigationPosition.equals("hospital")){
            spec1.setIndicator("Hospital", null);
        }
        else if(navigationPosition.equals("restaurant")){
            spec1.setIndicator("Restaurant", null);
        }
        else{
            spec1.setIndicator("Train station",null);
        }

        tabhost.addTab(spec1);

        /**initialize the second tab, which contains map**/
        TabHost.TabSpec spec2 = tabhost.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Map", null);
        tabhost.addTab(spec2);

        /**set openstreetmap in to mapView in second tab**/
        myOpenMapView = (MapView) findViewById(R.id.map);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setMultiTouchControls(true);

        myMapController = (MapController) myOpenMapView
                .getController();
        myMapController.setZoom(12);

        /**set the current location of the user
         * to the OpenStreetMap. **/
        GeoPoint center = new GeoPoint(latiOriginal, longiOriginal);
        myMapController.animateTo(center);


        /**initialize the overlayItem array list to
         * create multi markers in the map**/
        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        for (int j = 0; j < name.size(); j++) {

            /** In database latitute and longitude contains as one String
             *  split location string and get longitude and latitude as double values**/
            String[] s = location.get(j).split(",");

           /**add name of the place, distance between place
            * and user's current location and location of the place to the item**/
            anotherOverlayItemArray.add(new OverlayItem(
                    name.get(j), distanceArray.get(j).toString(), new GeoPoint(Double.parseDouble(s[0]), Double.parseDouble(s[1]))));

        }




        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay
                = new ItemizedIconOverlay<OverlayItem>(
                this, anotherOverlayItemArray, myOnItemGestureListener);
        myOpenMapView.getOverlays().add(anotherItemizedIconOverlay);

        /**Add Scale Bar to the OpenStreetMap **/
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        myOpenMapView.getOverlays().add(myScaleBarOverlay);

        /**Add MyLocationOverlay to the map**/
       myLocationOverlay = new MyLocationNewOverlay(this, myOpenMapView);

        myOpenMapView.getOverlays().add(myLocationOverlay);
        myOpenMapView.postInvalidate();
        System.out.println(myLocationOverlay.getLastFix()+" anu Location");
        CompassOverlay com= new CompassOverlay(this,myOpenMapView);
        myOpenMapView.getOverlays().add(com);
    }

    /**Implement OnItemGestureListener on OpenStreetMap to detect user touch
     * and retrieve touched items properties.
     * as properties user can see Toast which contains name of the place and
     * distance between user and that place**/
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener
            = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){

        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            Toast.makeText(ActivityPlaceListAndMap.this,
                    item.getTitle() + "\n" +"Distance: "+item.getSnippet()+" km",
                    Toast.LENGTH_LONG).show();
            return true;
        }

    };

    /** to get user's current location **/
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        myLocationOverlay.enableMyLocation();

        System.out.println("enable");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        myLocationOverlay.disableMyLocation();

        System.out.println("desable");
    }

    /**onclick method in suggestion button
     * when click the button go to suggestions activity
     * using bundle send position of the navigation drawer to the suggestion.class **/
        public void suggestPage(View v){
            Bundle simple_bundle=new Bundle();
            simple_bundle.putString("naviDrawer", navigationPosition);
            simple_bundle.putString("lati and longi",locationOriginal);
            simple_bundle.putString("file",fileName);
            Intent i=new Intent(ActivityPlaceListAndMap.this,ActivitySuggestions.class);
            i.putExtras(simple_bundle);
        startActivity(i);
         }



    /**get data from database by using Cursor object
     * get data according to users selection category **/
    public void getDataBase() {


        /** Do specific operations according to category **/
        if(navigationPosition.equals("bank")) {
            /** get data to cursor from the getBankDataFromDatabase method using DBHelper object **/
            cr = dbHelper.getBankDataFromDatabase(dbHelper,fileName);
            /**Move cursor to the first column in the database**/
            cr.moveToFirst();

            crString=cr.getColumnName(0);
            if( crString.equals("bank_id")) {
                /**Iterate until get last column of the database **/
                do {
                    /** add column data to the suitable variables**/
                    place_id=cr.getString(0);
                    place_name=cr.getString(2);
                    place_address=cr.getString(3);
                    place_location=cr.getString(4);

                    /** print the output in the console **/
                    System.out.println("ID " + cr.getString(0));
                    System.out.println("name " + cr.getString(2));
                    System.out.println("address " + cr.getString(3));
                    System.out.println("location " + cr.getString(4));

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

                    /** check whether distance is less than 8km
                     * if --> less == add related data in to specific arraylists to each data
                     * else --> do nothing. ignore the place **/
                    if (distance <= 92) {
                        sPlace=new SeachPlace(place_name,place_address," ","","",R.drawable.bank);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);
                        name.add(place_name);
                        web.add("");
                        addre.add(place_address);
                        tele.add("");
                        desc.add("");
                        location.add(place_location);
                        distanceArray.add(distance);

                    } else {

                        System.out.println("out or area "+ distance);
                    }

                } while (cr.moveToNext());

            }
        }

        else if(navigationPosition.equals("hotel")){
            cr = dbHelper.getHotelDataFromDatabase(dbHelper,fileName);
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

                    if (distance <= 92) {
                        sPlace=new SeachPlace(place_name,place_address,place_telephone,place_webSiteLink,place_description,R.drawable.hotel);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);

                        name.add(place_name);
                        web.add(place_webSiteLink);
                        addre.add(place_address);
                        tele.add(place_telephone);
                        desc.add(place_description);
                        location.add(place_location);
                        distanceArray.add(distance);
                    } else {
                        System.out.println("out or area "+ distance);
                    }


                } while (cr.moveToNext());


            }
        }

        else if(navigationPosition.equals("gas_station")){
            cr = dbHelper.getGasStationDataFromDatabase(dbHelper,fileName);
            cr.moveToFirst();
            crString=cr.getColumnName(0);
            if( crString.equals("gas_id")) {
                do {

                    place_id=cr.getString(0);
                    place_name=cr.getString(2);
                    place_address=cr.getString(3);
                    place_location=cr.getString(4);



                    System.out.println("ID " + cr.getString(0));
                    System.out.println("name " + cr.getString(2));
                    System.out.println("address " + cr.getString(3));
                    System.out.println("location " + cr.getString(4));
                    System.out.println(navigationPosition + " Gas");

                    String[] s = place_location.split(",");

                    latitute.add(s[0]);
                    longitude.add(s[1]);

                    System.out.println(latitute.get(i) + " lati of " + i);
                    System.out.println(longitude.get(i) + " Long of " + i);
                    distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
                    i++;

                    if (distance <= 100) {
                        sPlace=new SeachPlace(place_name,place_address," "," ","",R.drawable.gas);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);
                        name.add(place_name);
                        web.add("");
                        addre.add(place_address);
                        tele.add("");
                        desc.add("");
                        location.add(place_location);
                        distanceArray.add(distance);

                    } else {
                        System.out.println("out or area "+ distance);
                    }


                } while (cr.moveToNext());


            }
        }

        else if(navigationPosition.equals("historical_places")){
            cr = dbHelper.getHistPlacesStationDataFromDatabase(dbHelper,fileName);
            cr.moveToFirst();
            crString=cr.getColumnName(0);
            if( crString.equals("historicalplace_id")) {
                do {

                    place_id=cr.getString(0);
                    place_name=cr.getString(2);
                    place_address=cr.getString(3);
                    place_location=cr.getString(4);
                    place_description=cr.getString(5);



                    System.out.println("ID " + cr.getString(0));
                    System.out.println("name " + cr.getString(2));
                    System.out.println("address " + cr.getString(3));
                    System.out.println("des " + cr.getString(5));
                    System.out.println(navigationPosition + " Historical Place");

                    String[] s = place_location.split(",");

                    latitute.add(s[0]);
                    longitude.add(s[1]);

                    System.out.println(latitute.get(i) + " lati of " + i);
                    System.out.println(longitude.get(i) + " Long of " + i);
                    distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
                    i++;

                    if (distance <= 100) {
                        sPlace=new SeachPlace(place_name,place_address," "," ",place_description,R.drawable.touristattraction);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);
                        name.add(place_name);
                        web.add("");
                        addre.add(place_address);
                        tele.add("");

                        distanceArray.add(distance);
                        location.add(place_location);
                        desc.add(place_description);


                    } else {
                        System.out.println("out or area "+ distance);
                    }

                } while (cr.moveToNext());


            }
        }

        else if(navigationPosition.equals("hospital")){
            cr = dbHelper.getHospitalDataFromDatabase(dbHelper,fileName);
            cr.moveToFirst();
            crString=cr.getColumnName(0);
            if( crString.equals("hos_id")) {
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
                    System.out.println(navigationPosition + " Hospital");

                    String[] s = place_location.split(",");

                    latitute.add(s[0]);
                    longitude.add(s[1]);

                    System.out.println(latitute.get(i) + " lati of " + i);
                    System.out.println(longitude.get(i) + " Long of " + i);
                    distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
                    i++;

                    if (distance <= 100) {
                        sPlace=new SeachPlace(place_name,place_address,place_telephone,place_webSiteLink,"",R.drawable.hospital);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);
                        name.add(place_name);
                        web.add(place_webSiteLink);
                        addre.add(place_address);
                        tele.add(place_telephone);
                        desc.add(place_description);
                        location.add(place_location);
                        distanceArray.add(distance);

                    } else {
                        System.out.println("out or area "+ distance);
                    }


                } while (cr.moveToNext());


            }
        }

        else if(navigationPosition.equals("restaurant")){
            cr = dbHelper.getRestaurantDataFromDatabase(dbHelper,fileName);
            cr.moveToFirst();
            crString=cr.getColumnName(0);
            if( crString.equals("rest_id")) {
                do {

                    place_id=cr.getString(0);
                    place_name=cr.getString(2);
                    place_address=cr.getString(3);
                    place_location=cr.getString(4);
                    place_webSiteLink=cr.getString(5);
                    place_telephone=cr.getString(6);



                    System.out.println("ID " + cr.getString(0));
                    System.out.println("name " + cr.getString(2));
                    System.out.println("address " + cr.getString(3));
                    System.out.println("location " + cr.getString(4));
                    System.out.println(navigationPosition + " Restaurant");

                    String[] s = place_location.split(",");

                    latitute.add(s[0]);
                    longitude.add(s[1]);

                    System.out.println(latitute.get(i) + " lati of " + i);
                    System.out.println(longitude.get(i) + " Long of " + i);
                    distance = cordinates.distance(latiOriginal, longiOriginal, Double.parseDouble(latitute.get(i)), Double.parseDouble(longitude.get(i)));
                    i++;

                    if (distance <= 100) {
                        sPlace=new SeachPlace(place_name,place_address,place_telephone,place_webSiteLink,"",R.drawable.rest);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);

                        name.add(place_name);
                        web.add(place_webSiteLink);
                        addre.add(place_address);
                        tele.add(place_telephone);
                        desc.add("");
                        location.add(place_location);
                        distanceArray.add(distance);
                    } else {
                        System.out.println("out or area "+ distance);
                    }


                } while (cr.moveToNext());


            }
        }

        else if(navigationPosition.equals("train_station")){
            cr = dbHelper.getTrainStationDataFromDatabase(dbHelper,fileName);
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

                    if (distance <= 100) {
                        sPlace=new SeachPlace(place_name,place_address," "," ","",R.drawable.train);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);
                        name.add(place_name);
                        web.add("");
                        addre.add(place_address);
                        tele.add("");
                        desc.add("");
                        location.add(place_location);
                        distanceArray.add(distance);
                    } else {
                        System.out.println("out or area "+ distance);
                    }


                } while (cr.moveToNext());


            }
        }

        else if(navigationPosition.equals("bus_stop")){
            cr = dbHelper.getBusStopDataFromDatabase(dbHelper,fileName);
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

                    if (distance <= 100) {
                        sPlace=new SeachPlace(place_name,place_address," "," ","",R.drawable.bus);
                        placeList.add(sPlace);
                        System.out.println("Distance " + distance + " to " + i);
                        name.add(place_name);
                        web.add("");
                        addre.add(place_address);
                        tele.add("");
                        desc.add("");
                        location.add(place_location);
                        distanceArray.add(distance);
                    } else {
                        System.out.println("out or area "+ distance);
                    }
                } while (cr.moveToNext());
            }
        }

        else {
            System.out.println(" not workingggggg");
        }

    }

    private void EnableGPSIfPossible()
    {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
    }

    private  void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Yout GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();

    }
}




package project.guide.anu.travellanka.viewpackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import project.guide.anu.travellanka.ActivityMyLocation;
import project.guide.anu.travellanka.modelpackage.City;
import project.guide.anu.travellanka.controllerpackage.Communicator;
import project.guide.anu.travellanka.controllerpackage.GPSLocation;
import project.guide.anu.travellanka.R;
import project.guide.anu.travellanka.databasehandler.UserDBHelper;


public class ActivityCity_view extends ActionBarActivity implements Communicator {

    City city;
    GPSLocation gpsTracker;
    Button b;
    ImageView image;
    TextView name;
    TextView description;
    Button buttonDownload;
    String fileName;

    //variables for database
    UserDBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context con;
    String qury = "";
    public static ArrayList<String> list=new ArrayList<String>();

    // variables for navigation drawer
    String naviPosition;


    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);



        /**extract the city data from received bundle
         * and add it to city object**/

        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        city= (City) my_bundle_received.get("cityObject");
        fileName= (String) my_bundle_received.get("file");

        /** initialize the widgets **/
        name= (TextView) findViewById(R.id.city_name);
        image= (ImageView) findViewById(R.id.city_image);
        description= (TextView) findViewById(R.id.city_description);
        buttonDownload = (Button)findViewById(R.id.button);


       EnableGPSIfPossible();
        gpsTracker = new GPSLocation(ActivityCity_view.this);



        /** set data to text views to display data in the GUI
         * Which we get from previous Activity **/

        name.setText(city.getCity_name());
        image.setImageResource(city.getCity_image());
        description.setText(city.getCity_description());

        /** call method to read data file **/
        getDataFromFile();



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
                        buttonDownload.setEnabled(true);
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


    public void downloadButton(View v){
        /**This method use for test.
         *  get GPS location correctly and using toast show the GPS location**/
        Toast.makeText(ActivityCity_view.this, gpsTracker.getLatLng(), Toast.LENGTH_SHORT).show();

        Bundle simple_bundle=new Bundle();

        simple_bundle.putString("longi and latitu",gpsTracker.getLatLng());
        Intent i=new Intent(ActivityCity_view.this, ActivityMyLocation.class);
        i.putExtras(simple_bundle);
        startActivity(i);

    }
    //gps location
    @Override
    protected void onResume() {
        super.onResume();
        gpsTracker.requestUpdate();
    }

    @Override
    protected void onPause() {
        super.onPause();
         gpsTracker.removeUpdates();
    }



    public void getDataFromFile(){

        /** Read the data file
         * and add that data in to Array list **/

         File f = getFilesDir();
        String path = f.getAbsolutePath();

        try {
            /** get data file **/

            System.out.println(fileName+ " file name asameeraya maha wadayak");
            File myFile = new File("/sdcard/" + fileName);
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            StringBuffer b = new StringBuffer();

               /** add data to buffer **/
            while (bis.available() != 0) {
                char c = (char) bis.read();
                b.append(c);
            }

            /** scan buffered data**/
            Scanner sc = new Scanner(b.toString());
               list = new ArrayList<>();
            /**Read scanned data until it has no data**/
            while (sc.hasNext()) {

                String temp = sc.nextLine();
                if (temp.length() > 0) {
                    /**Check end of the database query using ';' symbol**/
                    if (temp.charAt(temp.length() - 1) == ';') {
                        qury += temp;

                        /** add sqlite query to arraylist**/
                        list.add(qury);
                        //System.out.println(qury);
                        qury = "\n";
                    } else {
                        qury += temp;

                    }

                }

            }







        } catch (Exception e) {
            System.out.println("file not found");
        }



        /**Initialize the context
         * create UserDBHelper object and call database**/
        con = this;
        dbHelper = new UserDBHelper(con);
        sqLiteDatabase = dbHelper.getWritableDatabase();




        dbHelper.insertNewData(list,fileName);





    }

    public void respond(String s) {

        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

        /** get the navigationdrawer position by using interface
         * and send that chategory of the position to the ActivityPlaceListAndMap activity
         * by putting that string to bundle. **/





        naviPosition=s;
        Bundle simple_bundle=new Bundle();
        simple_bundle.putString("NaviPosition",naviPosition);
        simple_bundle.putString("longi and latitu",gpsTracker.getLatLng());

        simple_bundle.putString("file",fileName);
        Intent intent=new Intent(ActivityCity_view.this,ActivityPlaceListAndMap.class);
        intent.putExtras(simple_bundle);
        startActivity(intent);
    }
}
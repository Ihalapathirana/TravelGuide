package project.guide.anu.travellanka.databasehandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import project.guide.anu.travellanka.controllerpackage.DatabaseConstants;
import project.guide.anu.travellanka.viewpackage.ActivityCity_view;

/**
 * Created by Anu on 8/17/2015.
 */
public class UserDBHelper extends SQLiteOpenHelper {

    /** initialize the variables**/
    private static final String DATABASE_NAME = "skhd";
    private static final int DATABASE_VERSION = 1;
    String qury;
    Cursor cr;
    Context ctx;


    ActivityCity_view m;

    /** constructor **/
    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("database operation ", "database created or open");
        ActivityCity_view m = new ActivityCity_view();
        ctx=context;
    }


    /**this method run only when one time. the time when we create the database. if database exist. it wll not run again
    create the table inside of oncreate method**/
    @Override
    public void onCreate(SQLiteDatabase db) {

        /**execute the queries on the array list in ActivityCity_View class**/
        for (int i = 0; i <m.list.size() ; i++) {
            db.execSQL(m.list.get(i));
            System.out.println(m.list.get(i)+" sameeraaaa");
        }

        DatabaseConstants.savecity(ctx, "colomboNEW.txt");


    }

    /** onUpgrade method to update the database **/
    @Override
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
        try {

            db.execSQL(" drop table if exists");
            onCreate(db);
        }catch (Exception e){
            System.out.println("OnUpgrade error");
        }
    }

    public void insertNewData(ArrayList<String> queryList,String dbCityName){

        if(!(DatabaseConstants.cityExists(ctx,dbCityName))){
            SQLiteDatabase db = this.getWritableDatabase();
            for (int i = 0; i <m.list.size() ; i++) {
                db.execSQL(m.list.get(i));

            }
            DatabaseConstants.savecity(ctx,dbCityName);
        }


    }

    /** method to get data from bank table**/
    public Cursor getBankDataFromDatabase(UserDBHelper dop,String file){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"bank_id","city_id","bank_name","address","location"};

        if(file.equals("gallesam.txt")){
            cr=sq.query("bank",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("bank",column,"city_id='2'",null,null,null,null);
        }

        return cr;

    }

    /** method to get data from hotel table**/
    public Cursor getHotelDataFromDatabase(UserDBHelper dop,String file){

        String pos=null;
        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"hotel_id","city_id","hotel_name","address","location","websitelink","telephone","description"};
        if(file.equals("gallesam.txt")){
            cr=sq.query("hotel",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("hotel",column,"city_id='2'",null,null,null,null);
        }



        return cr;

    }

    /** method to get data from gas Station table**/
    public Cursor getGasStationDataFromDatabase(UserDBHelper dop,String file){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"gas_id","city_id","gas_name","location","address"};

        if(file.equals("gallesam.txt")){
            cr=sq.query("gas_station",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("gas_station",column,"city_id='2'",null,null,null,null);
        }
        return cr;

    }

    /** method to get data from Attraction place table**/
    public Cursor getHistPlacesStationDataFromDatabase(UserDBHelper dop,String file){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"historicalplace_id","city_id","historicalplace_name","address","location","description"};


        if(file.equals("gallesam.txt")){
            cr=sq.query("historical_places",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("historical_places",column,"city_id='2'",null,null,null,null);
        }

        return cr;

    }

    /** method to get data from hospital table**/
    public Cursor getHospitalDataFromDatabase(UserDBHelper dop,String file){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"hos_id","city_id","hos_name","address","location","website","telephone","description"};

        if(file.equals("gallesam.txt")){
            cr=sq.query("hospital",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("hospital",column,"city_id='2'",null,null,null,null);
        }

        return cr;

    }

    /** method to get data from restourent table**/
    public Cursor getRestaurantDataFromDatabase(UserDBHelper dop,String file){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"rest_id","city_id","rest_name","address","location","websitelink","telephone"};
        if(file.equals("gallesam.txt")){
            cr=sq.query("restaurant",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("restaurant",column,"city_id='2'",null,null,null,null);
        }

        return cr;

    }

    /** method to get data from train station table**/
    public Cursor getTrainStationDataFromDatabase(UserDBHelper dop,String file){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"train_station_id","city_id","train_station_name","address","location"};

        if(file.equals("gallesam.txt")){
            cr=sq.query("train_station",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("train_station",column,"city_id='2'",null,null,null,null);
        }

        return cr;

    }

    /** method to get data from bus stops table**/
    public Cursor getBusStopDataFromDatabase(UserDBHelper dop,String file){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"busstop_id","city_id","busstop_name","location","address"};

        if(file.equals("gallesam.txt")){
            cr=sq.query("bus_stop",column,"city_id='1'",null,null,null,null);
        }
        else if(file.equals("colomboNEW.txt")){
            cr=sq.query("bus_stop",column,"city_id='2'",null,null,null,null);
        }

        return cr;

    }

}



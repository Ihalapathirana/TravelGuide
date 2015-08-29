package project.guide.anu.travellanka;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Anu on 8/17/2015.
 */
public class UserDBHelper extends SQLiteOpenHelper {

    /** initialize the variables**/
    private static final String DATABASE_NAME = "Colombo";
    private static final int DATABASE_VERSION = 1;
    String qury;


    ActivityCity_view m;

    /** constructor **/
    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("database operation ", "database created or open");
        ActivityCity_view m = new ActivityCity_view();
    }


    /**this method run only when one time. the time when we create the database. if database exist. it wll not run again
    create the table inside of oncreate method**/
    @Override
    public void onCreate(SQLiteDatabase db) {

        /**execute the queries on the array list in ActivityCity_View class**/
        for (int i = 0; i <m.list.size() ; i++) {
            db.execSQL(m.list.get(i));
        }


    }

    /** onUpgrade method to update the database **/
    @Override
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL(" drop table if exists anu ; ");
        onCreate(db);
    }

    /** method to get data from bank table**/
    public Cursor getBankDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"bank_id","city_id","bank_name","address","location"};
        Cursor cr=sq.query("bank",column,null,null,null,null,null);

        return cr;

    }

    /** method to get data from hotel table**/
    public Cursor getHotelDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"hotel_id","city_id","hotel_name","address","location","websitelink","telephone","description"};
        Cursor cr=sq.query("hotel",column,null,null,null,null,null);

        return cr;

    }

    /** method to get data from gas Station table**/
    public Cursor getGasStationDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"gas_id","city_id","gas_name","location","address"};
        Cursor cr=sq.query("gas_station",column,null,null,null,null,null);

        return cr;

    }

    /** method to get data from Attraction place table**/
    public Cursor getHistPlacesStationDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"historicalplace_id","city_id","historicalplace_name","address","location","description"};
        Cursor cr=sq.query("historical_places",column,null,null,null,null,null);

        return cr;

    }

    /** method to get data from hospital table**/
    public Cursor getHospitalDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"hos_id","city_id","hos_name","address","location","website","telephone","description"};
        Cursor cr=sq.query("hospital",column,null,null,null,null,null);

        return cr;

    }

    /** method to get data from restourent table**/
    public Cursor getRestaurantDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"rest_id","city_id","rest_name","address","location","websitelink","telephone"};
        Cursor cr=sq.query("restaurant",column,null,null,null,null,null);

        return cr;

    }

    /** method to get data from train station table**/
    public Cursor getTrainStationDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"train_station_id","city_id","train_station_name","address","location"};
        Cursor cr=sq.query("train_station",column,null,null,null,null,null);

        return cr;

    }

    /** method to get data from bus stops table**/
    public Cursor getBusStopDataFromDatabase(UserDBHelper dop){

        SQLiteDatabase sq=dop.getReadableDatabase();
        String[] column={"busstop_id","city_id","busstop_name","location","address"};
        Cursor cr=sq.query("bus_stop",column,null,null,null,null,null);

        return cr;

    }

}



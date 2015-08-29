package project.guide.anu.travellanka.controllerpackage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Anu on 8/27/2015.
 */
public class DatabaseConstants {



    public static void savecity(Context activity,String city){
        SharedPreferences preferences = activity.getSharedPreferences("MyPref",Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(city,city);
        editor.commit();
    }

    public static boolean cityExists(Context activity,String city){
        SharedPreferences preferences = activity.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
           String response=preferences.getString(city,"null");
        if(response.equals("null")){
            return false;
        }else{
            return true;
        }
    }

}

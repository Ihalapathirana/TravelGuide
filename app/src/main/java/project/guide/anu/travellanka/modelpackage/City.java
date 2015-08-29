package project.guide.anu.travellanka.modelpackage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anu on 6/21/2015.
 */
public class City implements Parcelable {
    private String city_name;
    private int city_image;
    private String city_description;

    //constructor
    public  City(String name,int image){
        setCity_image(image);
        setCity_name(name);
    }

    //constructor
    public City(String name,int image,String desc){

        setCity_name(name);
        setCity_image(image);
        setCity_description(desc);

    }

    public City(){

    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getCity_image() {
        return city_image;
    }

    public void setCity_image(int city_image) {
        this.city_image = city_image;
    }

    public String getCity_description() {
        return city_description;
    }

    public void setCity_description(String city_description) {
        this.city_description = city_description;
    }



    //to send object to next activity
    protected City(Parcel in) {
        city_name = in.readString();
        city_image = in.readInt();
        city_description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // to send object to the next activity
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city_name);
        dest.writeInt(city_image);
        dest.writeString(city_description);
    }

    @SuppressWarnings("unused")
    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
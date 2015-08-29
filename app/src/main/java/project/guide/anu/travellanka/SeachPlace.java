package project.guide.anu.travellanka;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anu on 6/24/2015.
 */
public class SeachPlace implements Parcelable {

    private String name;
    private String address;
    private String phone_no;
    private String description;
    private int image;
    private String webLink;


    public SeachPlace(String n, int i) {
        setName(n);
        setImage(i);
    }



    public SeachPlace(Parcel in) {

        name = in.readString();
        address = in.readString();
        phone_no = in.readString();
        webLink = in.readString();
        image = in.readInt();
        description=in.readString();
    }

    public SeachPlace(String n, String add, String pn, String weblink,String de, int i) {
        setName(n);
        setAddress(add);
        setPhone_no(pn);
        setWebLink(weblink);
        setImage(i);
        setDescription(de);
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.phone_no);
        dest.writeString(this.webLink);
        dest.writeInt(this.image);
        dest.writeString(this.description);

    }
    public static final Creator<SeachPlace> CREATOR = new Creator<SeachPlace>() {
        @Override
        public SeachPlace createFromParcel(Parcel in) {
            return new SeachPlace(in);
        }

        @Override
        public SeachPlace[] newArray(int size) {
            return new SeachPlace[size];
        }
    };


}
package project.guide.anu.travellanka;

import android.os.Parcel;

import junit.framework.Assert;
import junit.framework.TestCase;

import project.guide.anu.travellanka.modelpackage.SeachPlace;

/**
 * Created by Anu on 8/17/2015.
 */
public class SearchPlaceTest extends TestCase {

    public void testGetName() throws Exception {
        String name = "hospital";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setName(name);

        Assert.assertTrue(place.getName().equals(name));
    }

    public void testSetName() throws Exception {
        String name = "hospital";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setName(name);

        Assert.assertTrue(place.getName().equals(name));
    }

    public void testGetAddress() throws Exception {
        String address = "46/B, Hemas, Caolombo 7, Srilanka";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setAddress(address);

        Assert.assertTrue(place.getAddress().equals(address));
    }

    public void testSetAddress() throws Exception {
        String address = "46/B, Hemas, Caolombo 7, Srilanka";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setAddress(address);

        Assert.assertTrue(place.getAddress().equals(address));
    }

    public void testGetPhone_no() throws Exception {
        String phone = "0714652035";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setPhone_no(phone);

        Assert.assertTrue(place.getPhone_no().equals(phone));
    }

    public void testSetPhone_no() throws Exception {
        String phone = "0714652035";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setPhone_no(phone);

        Assert.assertTrue(place.getPhone_no().equals(phone));
    }

    public void testGetDescription() throws Exception {
        String desc = "private hospital in colombo city";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setDescription(desc);

        Assert.assertTrue(place.getDescription().equals(desc));
    }

    public void testSetDescription() throws Exception {
        String desc = "private hospital in colombo city";
        SeachPlace place=new SeachPlace(Parcel.obtain());
        place.setDescription(desc);

        Assert.assertTrue(place.getDescription().equals(desc));
    }
}

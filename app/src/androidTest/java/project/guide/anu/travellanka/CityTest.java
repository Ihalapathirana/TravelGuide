package project.guide.anu.travellanka;

import junit.framework.Assert;
import junit.framework.TestCase;

import project.guide.anu.travellanka.modelpackage.City;

/**
 * Created by Anu on 8/17/2015.
 */
public class CityTest extends TestCase {

    public void testGetCity_name() throws Exception {

        //given
        String name = "Galle";

        //When
        City city = new City();
        city.setCity_name(name);

        //Then
        Assert.assertTrue(city.getCity_name().equals(name));
    }

    public void testSetCity_name() throws Exception {

        String name = "Colombo";
        City city = new City();
        city.setCity_name(name);

        Assert.assertTrue(city.getCity_name().equals(name));
    }




    public void testGetCity_description() throws Exception {
        String desc = "this is colombo city";
        City city = new City();
        city.setCity_description(desc);

        Assert.assertTrue(city.getCity_description().equals(desc));
    }

    public void testSetCity_description() throws Exception {
        String desc = "this is colombo city";
        City city = new City();
        city.setCity_description(desc);

        Assert.assertTrue(city.getCity_description().equals(desc));
    }

    public void testDescribeContents() throws Exception {

    }

    public void testWriteToParcel() throws Exception {

    }
}

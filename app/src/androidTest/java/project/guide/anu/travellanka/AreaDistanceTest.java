package project.guide.anu.travellanka;

import junit.framework.Assert;
import junit.framework.TestCase;

import project.guide.anu.travellanka.controllerpackage.AreaDistance;

/**
 * Created by Anu on 8/19/2015.
 */
public class AreaDistanceTest extends TestCase {

    public void testDeg2rad() throws Exception {


        double dist=180;
        String distanceget;

        AreaDistance areaDistance=new AreaDistance();
        distanceget= String.valueOf(areaDistance.deg2rad(dist));
        String s= String.valueOf(Math.PI);

        Assert.assertTrue(distanceget.equals(s));
    }


    public void testgetrad2deg() throws Exception {

        double distanceget;

        AreaDistance areaDistance=new AreaDistance();
        distanceget= areaDistance.rad2deg(Math.PI);
        String s= String.valueOf(180);

        Assert.assertEquals(180.00,distanceget,0.1);

    }


    public void testdistance(){

        double distanceget;

        AreaDistance areaDistance=new AreaDistance();
        distanceget= areaDistance.distance(6.7969053,6.761187,79.8972277,79.900109);

        Assert.assertEquals(8937.908686424833,distanceget,1);
    }
}

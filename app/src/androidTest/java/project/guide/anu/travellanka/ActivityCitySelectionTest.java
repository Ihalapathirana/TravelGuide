package project.guide.anu.travellanka;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import project.guide.anu.travellanka.viewpackage.ActivityCitySelection;

/**
 * Created by Anu on 8/17/2015.
 */
public class ActivityCitySelectionTest extends ActivityInstrumentationTestCase2<ActivityCitySelection> {

    private Solo solo;

    public ActivityCitySelectionTest() {

        super(ActivityCitySelection.class);
    }



    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo=new Solo(getInstrumentation(),getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testListItemClickShouldDisplay() throws Exception {
        // check that we have the right activity
        solo.assertCurrentActivity("wrong activity", ActivityCitySelection.class);

        // Click a button which will start a new Activity
        // Here use the ID of the string to find the right button

        // assert that the current activity is the SimpleListActivity.class
        solo.assertCurrentActivity("wrong activity", ActivityCitySelection.class);
        solo.clickInList(1);
        solo.goBack();
        // searchForText has a timeout of 5 seconds

        solo.clickInList(2);
        solo.goBack();

        solo.clickInList(3);
        solo.goBack();







    }


}

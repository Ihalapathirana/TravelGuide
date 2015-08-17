package project.guide.anu.travellanka;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by Anu on 8/17/2015.
 */
public class ActivitySuggestionsTest extends ActivityInstrumentationTestCase2<ActivitySuggestions> {

    private Solo solo;

    public ActivitySuggestionsTest() {

        super(ActivitySuggestions.class);
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

    public void testListItemClick() throws Exception {
        // check that we have the right activity
        solo.assertCurrentActivity("wrong activity", ActivitySuggestions.class);

        // Click a button which will start a new Activity
        // Here use the ID of the string to find the right button

        // assert that the current activity is the SimpleListActivity.class
        solo.assertCurrentActivity("wrong activity", ActivitySuggestions.class);
        solo.clickInList(1);
        solo.clickInList(2);
        solo.clickInList(3);








    }


}

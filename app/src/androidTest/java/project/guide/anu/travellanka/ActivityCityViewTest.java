package project.guide.anu.travellanka;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by Anu on 8/17/2015.
 */
public class ActivityCityViewTest extends ActivityInstrumentationTestCase2<ActivityCity_view> {

    private Solo solo;

    public ActivityCityViewTest() {

        super(ActivityCity_view.class);
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

    public void testListShouldDisplay() throws Exception {

        solo = new Solo(getInstrumentation(), getActivity());
        solo.setNavigationDrawer(Solo.OPENED);
    }


}

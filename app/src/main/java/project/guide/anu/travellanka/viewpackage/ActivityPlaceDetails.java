package project.guide.anu.travellanka.viewpackage;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.guide.anu.travellanka.R;
import project.guide.anu.travellanka.modelpackage.SeachPlace;


public class ActivityPlaceDetails extends ActionBarActivity {

    SeachPlace sPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_place_details);

        /** get details of the place which send in previous activity **/
        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        sPlace= (SeachPlace) my_bundle_received.get("searchPlace");

        /** Initialize the GUI textviews**/
        System.out.println(sPlace.getName()+ " name");
        TextView name= (TextView) findViewById(R.id.place_name);
        TextView description= (TextView) findViewById(R.id.descrp);
        TextView telephone= (TextView) findViewById(R.id.tele);
        TextView link= (TextView) findViewById(R.id.webLink);
        TextView address= (TextView) findViewById(R.id.addre);
        ImageView image= (ImageView) findViewById(R.id.city_image);

        /** set data to the GUI**/
        name.setText(sPlace.getName());
        description.setText(sPlace.getDescription());
        telephone.setText(sPlace.getPhone_no());
        link.setText(sPlace.getWebLink());
        address.setText(sPlace.getAddress());
        System.out.println(sPlace.getDescription()+ " description in place de");

    }


    /**when click the url link,
     * go to the website **/
    public void  onUrlClick(View view){

        /**set the data of url to send to the webView activity**/
        Bundle simple_bundle = new Bundle();
        simple_bundle.putString("item1", sPlace.getWebLink());

        /** create intent to webView activity**/
        Intent intent = new Intent(ActivityPlaceDetails.this, WebSite.class);
        intent.putExtras(simple_bundle);
        startActivity(intent);
    }
}

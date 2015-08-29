package project.guide.anu.travellanka;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import project.guide.anu.travellanka.R;


public class WebSite extends ActionBarActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_site);

        /** receive the bundle contains url
         * from placeDetails activity**/
        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        url= (String) my_bundle_received.get("item1");

        /** initialize and load the webView to url**/
        WebView web= (WebView) findViewById(R.id.webv1);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);
    }



}

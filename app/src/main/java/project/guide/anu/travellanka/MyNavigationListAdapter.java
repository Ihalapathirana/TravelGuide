package project.guide.anu.travellanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anu on 6/24/2015.
 */
public class MyNavigationListAdapter extends ArrayAdapter {

    public MyNavigationListAdapter(Context context, ArrayList<SeachPlace> arrayList) {
        super(context,0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeachPlace sPlace=(SeachPlace)getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.search_place_item,parent,false);//listitem.xml name

        }
        TextView name= (TextView)convertView.findViewById(R.id.place_name);
        ImageView cityImage= (ImageView) convertView.findViewById(R.id.imageView);


        name.setText(sPlace.getName());
        cityImage.setImageResource(sPlace.getImage());
        return convertView;
    }
}

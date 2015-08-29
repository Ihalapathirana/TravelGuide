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
 * Created by Anu on 6/25/2015.
 */
public class MyPlaceListAddapter extends ArrayAdapter {
    public MyPlaceListAddapter(Context context, ArrayList<SeachPlace> arrayList) {
        super(context, 0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeachPlace sPlace=(SeachPlace)getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.place_list_item,parent,false);//listitem.xml name

        }
        TextView name= (TextView)convertView.findViewById(R.id.name);
        TextView address= (TextView) convertView.findViewById(R.id.address);
        TextView phoneNo=(TextView)convertView.findViewById(R.id.tpnumber);
        TextView desc=(TextView)convertView.findViewById(R.id.desc);
        ImageView cityImage= (ImageView) convertView.findViewById(R.id.imageView);


        name.setText(sPlace.getName());
        address.setText(sPlace.getAddress());
        phoneNo.setText(sPlace.getPhone_no());
        desc.setText(sPlace.getWebLink());
        cityImage.setImageResource(sPlace.getImage());
        return convertView;
    }
}

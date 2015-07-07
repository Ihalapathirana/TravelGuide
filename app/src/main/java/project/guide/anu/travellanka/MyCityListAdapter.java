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
 * Created by Anu on 6/21/2015.
 */
public class MyCityListAdapter extends ArrayAdapter{
    public MyCityListAdapter(Context context, ArrayList<City> arrayList) {
        super(context,0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city=(City)getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.city_list_item,parent,false);//listitem.xml name

        }
    //this is not activity class so cannot wirte findviewById directly

        TextView name= (TextView)convertView.findViewById(R.id.name);
        ImageView cityImage= (ImageView) convertView.findViewById(R.id.cityImage);


        name.setText(city.getCity_name());
        cityImage.setImageResource(city.getCity_image());
        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

}
